package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

/**
 * RouteResponnseDTO represents route information received from Geoapify.
 */
public record RouteResponseDTO (
     List<FeatureDTO> features
) {

    public record FeatureDTO (
        PropertiesDTO properties,
        GeometryDTO geometry
    ) {}

    public record PropertiesDTO(
        double distance,
        int time,
        List<LegDTO> legs
    ) {}

    public record LegDTO(List<StepDTO> steps) {}

    public record StepDTO(InstructionDTO instruction) {}

    public record InstructionDTO(String text) {}
}
