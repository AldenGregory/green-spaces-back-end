package io.github.aldengregory.greenspaces.components;

import org.springframework.stereotype.Component;

import io.github.aldengregory.greenspaces.dtos.GreenSpaceDTO;
import io.github.aldengregory.greenspaces.entities.GreenSpaceEntity;

@Component
public class GreenSpaceConverter {
    /**
     * Builds a GreenSpaceDTO from a GreenSpaceEntity.
     * 
     * @param entity   The GreenSpaceEntity from which the data to build a
     *                 GreenSpaceDTO will be sourced from.
     * @return A GreenSpaceDTO with data corresponding to the entity parameter.
     */
    public GreenSpaceDTO fromGreenSpaceEntity(GreenSpaceEntity entity) {
        return new GreenSpaceDTO(
            entity.getId(),
            entity.getParkName(), 
            entity.getLatitude(),
            entity.getLongitude(),
            entity.getDescription()
        );
    }
}
