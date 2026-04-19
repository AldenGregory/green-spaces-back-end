package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

/**
 * RouteResultDTO is a simplified formatting of a RouteResponseDTO.
 */
public record RouteResultDTO (
    double distance,
    int time,
    List<StepInfoDTO> steps,
    GeometryResultDTO geometry
) {
    public record StepInfoDTO(
        int minutes,
        String instruction,
        double startLatitude,
        double startLongitude
    ) {}
}
