package io.github.aldengregory.greenspaces.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * GreenSpaceEntity corresponds to green space entries in the database.
 * 
 * This class is used when retrieving information on green spaces from the
 * database.
 */
@Entity
@Getter
@Table(name = "green_space_information")
public class GreenSpaceEntity {
    @Id
    private Long id;
    private String parkName;
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private double latitude;
    private double longitude;
    private String description;
}
