package io.github.aldengregory.greenspaces.dtos;

import io.github.aldengregory.greenspaces.entities.GreenSpaceEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * GreenSpaceDTO models important green space information for the front end.
 * 
 * Data for this class can be filled in from a GreenSpaceEntity. This is
 * effectively a simplified GreenSpaceEntity tailored to front-end needs.
 */
@RequiredArgsConstructor
@Getter
public class GreenSpaceDTO {
    private final String parkName;
    private final double latitude;
    private final double longitude;
    private final String description;

    /**
     * Builds a GreenSpaceDTO from a GreenSpaceEntity.
     * 
     * @param entity The GreenSpaceEntity from which the data to build a
     *               GreenSpaceDTO will be sourced from.
     * @return A GreenSpaceDTO with data corresponding to the entity parameter.
     */
    public static GreenSpaceDTO fromGreenSpaceEntity(GreenSpaceEntity entity) {
        return new GreenSpaceDTO(
            entity.getParkName(), 
            entity.getLatitude(),
            entity.getLongitude(),
            entity.getDescription()
        );
    }
}
