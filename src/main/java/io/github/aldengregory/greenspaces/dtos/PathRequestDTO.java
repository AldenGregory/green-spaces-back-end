package io.github.aldengregory.greenspaces.dtos;

/**
 * PathDTO models a simple path.
 * 
 * This record stores information on the source and destination coordinates in
 * a path. It also stores the desired language for a route. This information
 * can be used to determine the route between them.
 */

public record PathRequestDTO(
    double sourceLatitude,
    double sourceLongitude,
    double destinationLatitude,
    double destinationLongitude,
    String language
) {}
