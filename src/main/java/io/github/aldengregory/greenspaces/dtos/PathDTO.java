package io.github.aldengregory.greenspaces.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * PathDTO models a simple path.
 * 
 * This class stores information on the source and destination coordinates in
 * a path. This information can be used to determine the route between them.
 */
@Getter
@Setter
public class PathDTO {
    private double sourceLatitude;
    private double sourceLongitude;
    private double destinationLatitude;
    private double destinationLongitude;
}
