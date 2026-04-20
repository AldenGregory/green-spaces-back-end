package io.github.aldengregory.greenspaces.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.aldengregory.greenspaces.components.GreenSpaceConverter;
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
    private final GreenSpaceRepository greenSpaceRepo;
    private final GreenSpaceConverter responseConverter;
    private final TranslationService translationService;

    /**
     * Creates a GreenSpaceService that can access database information with a
     * GreenSpaceRepository.
     * 
     * @param greenSpaceRepo The repository that this service uses to access
     *                       the GreenSpaces database.
     */
    public GreenSpaceService(
        GreenSpaceRepository greenSpaceRepo,
        GreenSpaceConverter responseConverter,
        TranslationService translationService
    ) {
        this.greenSpaceRepo = greenSpaceRepo;
        this.responseConverter = responseConverter;
        this.translationService = translationService;
    }

    /**
     * Retrieves all green spaces from the database and converts them to
     * GreenspaceDTOs.
     * 
     * @param langauge The preferred language of the user making the request.
     * @return A list of GreenSpaceDTOs for all green spaces in the database.
     */
    public List<GreenSpaceDTO> getGreenSpaces() {
        List<GreenSpaceEntity> greenSpaceEntities = greenSpaceRepo.findAll();

        List<GreenSpaceDTO> greenSpaces = new ArrayList<>();

        // Convert greenSpaceEntities to greenSpaceDTOs.
        for (GreenSpaceEntity currentInformation : greenSpaceEntities) {
            greenSpaces.add(
                responseConverter.fromGreenSpaceEntity(currentInformation)
            );
        }

        return greenSpaces;
    }

    public String translateDescription(String language, String description) {
        // Avoid error of translating English to English.
        if (!"en".equals("language")) {
            return translationService.translateString(description, language);
        }

        return description;
    }
}
