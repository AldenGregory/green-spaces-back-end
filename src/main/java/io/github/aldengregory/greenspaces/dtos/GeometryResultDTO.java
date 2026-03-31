package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

public record GeometryResultDTO(
    String type,
    List<GeoFeatureDTO> features
) {
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

