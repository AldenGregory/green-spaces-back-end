package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

/**
 * RouteResultDTO is a simplified formatting of a RouteResponseDTO.
 */
public record RouteResultDTO (
    double distance,
    int time,
    List<String> instructions,
    GeometryResultDTO geometry
) {}
