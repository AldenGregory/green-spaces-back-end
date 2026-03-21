package io.github.aldengregory.greenspaces.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GreenSpaceDTO {
    private final String parkName;
    private final double latitude;
    private final double longitude;
    private final String description;
}
