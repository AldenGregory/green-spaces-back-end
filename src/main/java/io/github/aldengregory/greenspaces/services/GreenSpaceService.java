package io.github.aldengregory.greenspaces.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.aldengregory.greenspaces.dtos.GreenSpaceDTO;
import io.github.aldengregory.greenspaces.entities.GreenSpaceEntity;
import io.github.aldengregory.greenspaces.repositories.GreenSpaceRepository;

/**
 * GreenSpaceService handles business logic associated with green spaces.
 * 
 * This class accesses the database using a GreenSpaceRepository. The
 * GreenSpaceController class uses this class to handle its logic.
 */
@Service
public class GreenSpaceService {
    private GreenSpaceRepository greenSpaceRepo;

    /**
     * Creates a GreenSpaceService that can access database information with a
     * GreenSpaceRepository.
     * 
     * @param greenSpaceRepo The repository that this service uses to access
     *                       the GreenSpaces database.
     */
    public GreenSpaceService(GreenSpaceRepository greenSpaceRepo) {
        this.greenSpaceRepo = greenSpaceRepo;
    }

    /**
     * Retrieves all green spaces from the database and converts them to
     * GreenspaceDTOs.
     * 
     * @return A list of GreenSpaceDTOs for all green spaces in the database.
     */
    public List<GreenSpaceDTO> getGreenSpaces() {
        List<GreenSpaceEntity> greenSpaceEntities = greenSpaceRepo.findAll();

        List<GreenSpaceDTO> greenSpaces = new ArrayList<>();

        // Convert greenSpaceEntities to greenSpaceDTOs.
        for (GreenSpaceEntity currentInformation : greenSpaceEntities) {
            greenSpaces.add(
                GreenSpaceDTO.fromGreenSpaceEntity(currentInformation)
            );
        }

        return greenSpaces;
    }
}
