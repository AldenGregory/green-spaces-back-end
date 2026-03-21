package io.github.aldengregory.greenspaces.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.aldengregory.greenspaces.dtos.GreenSpaceDTO;
import io.github.aldengregory.greenspaces.entities.GreenSpaceEntity;
import io.github.aldengregory.greenspaces.repositories.GreenSpaceInformationRepository;

@Service
public class GreenSpaceService {
    private GreenSpaceInformationRepository greenSpaceRepo;

    public GreenSpaceService(GreenSpaceInformationRepository greenSpaceRepo) {
        this.greenSpaceRepo = greenSpaceRepo;
    }

    public List<GreenSpaceDTO> getGreenSpaces() {
        List<GreenSpaceEntity> greenSpaceEntities = greenSpaceRepo.findAll();

        List<GreenSpaceDTO> greenSpaces = new ArrayList<>();

        // Convert greenSpaceEntities to greenSpaceDTOs.
        for (GreenSpaceEntity currentInformation : greenSpaceEntities) {
            greenSpaces.add(
                new GreenSpaceDTO(
                    currentInformation.getParkName(),
                    currentInformation.getLongitude(),
                    currentInformation.getLatitude(),
                    currentInformation.getDescription()
                )
            );
        }

        return greenSpaces;
    }
}
