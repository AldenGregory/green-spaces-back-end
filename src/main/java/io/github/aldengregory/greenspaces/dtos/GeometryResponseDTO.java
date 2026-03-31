package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

public record GeometryResponseDTO(
    String type,
    List<List<List<Double>>> coordinates
) {}