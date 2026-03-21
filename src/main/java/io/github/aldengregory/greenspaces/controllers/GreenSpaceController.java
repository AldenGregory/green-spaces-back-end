package io.github.aldengregory.greenspaces.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import io.github.aldengregory.greenspaces.dtos.GreenSpaceDTO;
import io.github.aldengregory.greenspaces.services.GreenSpaceService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class GreenSpaceController {
    GreenSpaceService greenspaceService;

    public GreenSpaceController(GreenSpaceService greenspaceService) {
        this.greenspaceService = greenspaceService;
    }

    /**
     * Gets all information on all green spaces in the database.
     * 
     * @return A list of every green space information entry in the database.
     */
    @GetMapping("/greenspaces")
    public List<GreenSpaceDTO> getAll() {
        return greenspaceService.getGreenSpaces();
    }
    
}
