package io.github.aldengregory.greenspaces.dtos;

import java.util.List;

public record GeometryDTO(
    String type,
    List<List<List<Double>>> coordinates
) {}