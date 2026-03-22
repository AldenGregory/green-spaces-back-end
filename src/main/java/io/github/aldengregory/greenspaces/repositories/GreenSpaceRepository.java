package io.github.aldengregory.greenspaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.aldengregory.greenspaces.entities.GreenSpaceEntity;

/**
 * FreenSpaceRepository can access green spaces from the database.
 */
public interface GreenSpaceRepository extends JpaRepository<GreenSpaceEntity, Long> { }
