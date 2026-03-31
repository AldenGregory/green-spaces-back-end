package io.github.aldengregory.greenspaces.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.github.aldengregory.greenspaces.dtos.GeometryResultDTO;
import io.github.aldengregory.greenspaces.dtos.GeometryResultDTO.FeatureGeometryDTO;
import io.github.aldengregory.greenspaces.dtos.GeometryResultDTO.GeoFeatureDTO;
import io.github.aldengregory.greenspaces.dtos.GeometryResultDTO.GeoPropertiesDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.FeatureDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.LegDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.PropertiesDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.StepDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResultDTO;

@Component
public class RouteConverter {
    /**
     * Restructures the a RouteResponseDTO into a RouteResultDTO.
     * 
     * The RouteResponseDTO structure is not as useful to the front end as that
     * of the RouteResultDTO. Specifically, RouteResultDTO more clearly
     * provides access to information needed by the front end and provides a
     * GeoJSON that can be more easily styled differently across steps.
     * 
     * @param response A RouteResponseDTO based on a Geoapify response to a
     *                 route request.
     * @return A RouteResultDTO build from the information provided by a
     *         RouteResponseDTO.
     */
     public RouteResultDTO fromRouteResponseDTO(RouteResponseDTO response) {
        // A simple route only has one feature for the single route requested.
        FeatureDTO routeFeature = response.features().get(0);
        PropertiesDTO routeProperties = routeFeature.properties();

        // A simple route between two points only has one leg.
        LegDTO routeLeg = routeProperties.legs().get(0);

        List<String> instructions = new ArrayList<String>();

        // Add all instruction text from StepDTOs.
        for (StepDTO step : routeLeg.steps()) {
            instructions.add(step.instruction().text());
        }

        return new RouteResultDTO(
            routeProperties.distance(),
            routeProperties.time(),
            instructions,
            separateGeoJsonSteps(response)
        );
    }

    /**
     * Separates GeoJSON route information by steps.
     * 
     * This method converts a GeoJSON where an entire route is specified with a
     * single MultiLineString to a FeatureCollection where each feature is
     * associated with a single step in the route. Features contain route
     * geometry usign a LineString and contain other properties associated with
     * the step they represent. This allows the GeoJSON to styled at the step
     * level rather than the route level in the front end. 
     * 
     * @param response A RouteResponseDTO with necessary information about a
     *                 route in the format in which it was received from the
     *                 Geoapify Routes API.
     * @return A GeometryResultDTO representing a GeoJSON where features are
     *         correlated with individual steps in a route.
     */
     private GeometryResultDTO separateGeoJsonSteps(RouteResponseDTO response) {
        FeatureDTO routeFeature = response.features().get(0);
        PropertiesDTO routeProperties = routeFeature.properties();

        // A simple route between two points only has one leg.
        LegDTO routeLeg = routeProperties.legs().get(0);

        // There should only be one list of route coordinates.
        List<List<Double>> routeCoordinates = routeFeature.geometry().coordinates().get(0);

        List<GeoFeatureDTO> features = new ArrayList<>();

        // Build each step as a separate feature in the response DTO.
        for (StepDTO step : routeLeg.steps()) {
            features.add(featureFromStep(step, routeCoordinates));
        }

        // Return built GeometryResultDTO.
        return new GeometryResultDTO(
            "FeatureCollection",
            features
        );
    }

    /**
     * Builds a GeoFeatureDTO from information in a StepDTO.
     * 
     * This method builds a specific feature within a larger GeoJSON that is a
     * FeatureCollection. The feature built will be associated with a specific
     * step.
     * 
     * @param step             The specific step the GeoFeatureDTO to be built
     *                         represents.
     * @param routeCoordinates The list of coordinates specifying the overall
     *                         route from which coordinates for an individual
     *                         step will be drawn.
     * @return A GeoFeatureDTO representing a specific step.
     */
    private GeoFeatureDTO featureFromStep(StepDTO step, List<List<Double>> routeCoordinates) {
        // Build geometry information for step.
        FeatureGeometryDTO featureGeometry = getStepGeometry(step, routeCoordinates);
        // Set properties for step feature.
        GeoPropertiesDTO featureProperties = new GeoPropertiesDTO(getSimplifiedStepMode(step));

        return new GeoFeatureDTO(
            "Feature",
            featureGeometry,
            featureProperties
        );
    }

    /**
     * Builds the FeatureGeometryDTO for a specific step.
     * 
     * This method uses the series of coordinates in the overall route
     * geometry to determien the geomtry for a specific step.
     * 
     * @param step             The step for which a FeatureGeometryDTO is being
     *                         built.
     * @param routeCoordinates The list of coordinates specifying the overall
     *                         route from which coordinates specific to a step
     *                         will be sourced.
     * @return A FeatureGeometryDTO representing the geometry specific to an
     *         individual step in a route.
     */
    private FeatureGeometryDTO getStepGeometry(StepDTO step, List<List<Double>> routeCoordinates) {
        List<List<Double>> featureCoordinates = new ArrayList<>();

        // Add all coordinates that are part of current step.
        for (int i = step.fromIndex(); i <= step.toIndex(); i++) {
            featureCoordinates.add(routeCoordinates.get(i));
        }

        FeatureGeometryDTO featureGeometry = new FeatureGeometryDTO(
            "LineString", 
            featureCoordinates
        );

        return featureGeometry;
    }

    /**
     * Simplifies instruction types for a step into more general categories.
     * 
     * For the purposes of this program, the only mode categories needed for a
     * step are transit and walk.
     * 
     * @param step The step from which the simplified mode is drawn.
     * @return A String that is the simplified mode for a step.
     */
    private String getSimplifiedStepMode(StepDTO step) {
        String stepType = step.instruction().type();
        String mode;

        if (
            "Transit".equals(stepType) || 
            "TransitRemainOn".equals(stepType) ||
            "TransitTransfer".equals(stepType)) {
            mode = "transit";
        } else {
            mode = "walk";
        }

        return mode;
    }
}
