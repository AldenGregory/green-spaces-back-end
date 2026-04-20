package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

public record ReachablePolygonResultDTO(
    String type,
    PolygonGeometryResultDTO geometry
) {
    public record PolygonGeometryResultDTO(
        String type,
        List<List<List<Double>>> coordinates
    ) {}
}
