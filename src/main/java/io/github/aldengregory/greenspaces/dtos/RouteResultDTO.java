package io.github.aldengregory.greenspaces.dtos;

import java.util.ArrayList;
import java.util.List;

import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.FeatureDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.LegDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.PropertiesDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO.StepDTO;

/**
 * RouteResultDTO is a simplified formatting of a RouteResponseDTO.
 */
public record RouteResultDTO (
    double distance,
    int time,
    List<String> instructions,
    GeometryDTO geometry
) {
    public static RouteResultDTO fromRouteResponseDTO(RouteResponseDTO response) {
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
            routeFeature.geometry()
        );
    }
}
