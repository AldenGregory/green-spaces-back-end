package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

public record ReachablePolygonResponseDTO(
    List<PolygonFeatureDTO> features
) {
    public record PolygonFeatureDTO(
        PolygonGeometryResponseDTO geometry
    ) {}

    public record PolygonGeometryResponseDTO(
        List<List<List<List<Double>>>> coordinates
    ) {}
}
