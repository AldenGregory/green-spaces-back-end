package io.github.aldengregory.greenspaces.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
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
