package io.github.aldengregory.greenspaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.aldengregory.greenspaces.entities.GreenSpaceEntity;

public interface GreenSpaceInformationRepository extends JpaRepository<GreenSpaceEntity, Long> { }
