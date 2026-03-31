package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * RouteResponseDTO represents route information received from Geoapify.
 */
public record RouteResponseDTO (
     List<FeatureDTO> features
) {

    public record FeatureDTO (
        PropertiesDTO properties,
        GeometryResponseDTO geometry
    ) {}

    public record PropertiesDTO(
        double distance,
        int time,
        List<LegDTO> legs
    ) {}

    public record LegDTO(List<StepDTO> steps) {}


    public record StepDTO(
        @JsonAlias("from_index") int fromIndex, 
        @JsonAlias("to_index")int toIndex, 
        InstructionDTO instruction
    ) {}

    public record InstructionDTO(String text, String type) {}
}
