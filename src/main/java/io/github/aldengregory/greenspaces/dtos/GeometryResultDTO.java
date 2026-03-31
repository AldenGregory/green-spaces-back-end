package io.github.aldengregory.greenspaces.dtos;

import java.util.ArrayList;
import java.util.List;

import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.FeatureDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.LegDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.PropertiesDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.StepDTO;

public record GeometryResultDTO(
    String type,
    List<GeoFeatureDTO> features
) {
    public static GeometryResultDTO fromRouteResponseDTO(RouteResponseDTO response) {
        FeatureDTO routeFeature = response.features().get(0);
        PropertiesDTO routeProperties = routeFeature.properties();

        // A simple route between two points only has one leg.
        LegDTO routeLeg = routeProperties.legs().get(0);

        List<List<Double>> routeCoordinates = routeFeature.geometry().coordinates().get(0);

        List<GeoFeatureDTO> features = new ArrayList<>();

        // Build each step as a separate feature in the response DTO.
        for (StepDTO step : routeLeg.steps()) {
            // Build geometry for this step.
            List<List<Double>> featureCoordinates = new ArrayList<>();

            // Add all coordiantes that are part of current step.
            for (int i = step.fromIndex(); i <= step.toIndex(); i++) {
                featureCoordinates.add(routeCoordinates.get(i));
            }

            FeatureGeometryDTO featureGeometry = new FeatureGeometryDTO(
                "LineString", 
                featureCoordinates
            );

            // Determine mode for this step.
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

            GeoPropertiesDTO featureProperties = new GeoPropertiesDTO(mode);

            features.add(
                new GeoFeatureDTO(
                    "Feature", 
                    featureGeometry, 
                    featureProperties
                )
            );
        }

        // Return built GeometryResultDTO.
        return new GeometryResultDTO(
            "FeatureCollection",
            features
        );
    }

    public record GeoFeatureDTO(
        String type,
        FeatureGeometryDTO geometry,
        GeoPropertiesDTO properties
    ){}

    public record FeatureGeometryDTO(
        String type,
        List<List<Double>> coordinates
    ) {}

    // For now, only use "transit" or "walk".
    public record GeoPropertiesDTO(
        String mode
    ) {}
}

