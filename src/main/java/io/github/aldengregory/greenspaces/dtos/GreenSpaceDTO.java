package io.github.aldengregory.greenspaces.dtos;

import io.github.aldengregory.greenspaces.entities.GreenSpaceEntity;

/**
 * GreenSpaceDTO models important green space information for the front end.
 * 
 * Data for this class can be filled in from a GreenSpaceEntity. This is
 * effectively a simplified GreenSpaceEntity tailored to front-end needs.
 */
public record GreenSpaceDTO(
    long id,
    String parkName,
    double latitude,
    double longitude,
    String description
) { }
