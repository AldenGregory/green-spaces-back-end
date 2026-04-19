package io.github.aldengregory.greenspaces.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import io.github.aldengregory.greenspaces.dtos.GreenSpaceDTO;
import io.github.aldengregory.greenspaces.services.GreenSpaceService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * GreenSpaceController handles REST endpoints related to green space access.
 * 
 * This class serves as the layer between the front end and any information
 * on green spaces in the database.
 */
@RestController
public class GreenSpaceController {
    private final GreenSpaceService greenSpaceService;

    /**
     * Creates a GreenSpaceController that uses a specific GreenSpaceService to
     * handle requests.
     * 
     * @param greenspaceService The service a GreenSpaceController will use.
     */
    public GreenSpaceController(GreenSpaceService greenSpaceService) {
        this.greenSpaceService = greenSpaceService;
    }

    /**
     * Gets all information on all green spaces in the database.
     * 
     * @return A list of every green space information entry in the database.
     */
    @GetMapping("/green-spaces")
    public List<GreenSpaceDTO> getAll() {
        return greenSpaceService.getGreenSpaces();
    }
    
}
