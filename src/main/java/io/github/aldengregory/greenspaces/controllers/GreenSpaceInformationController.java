package io.github.aldengregory.greenspaces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import io.github.aldengregory.greenspaces.entities.GreenSpaceInformation;
import io.github.aldengregory.greenspaces.repositories.GreenSpaceInformationRepository;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class GreenSpaceInformationController {
    @Autowired
    GreenSpaceInformationRepository greenspaceRepo;

    /**
     * Gets all information on all green spaces in the database.
     * 
     * @return A list of every green space information entry in the database.
     */
    @GetMapping("/greenspaces")
    public List<GreenSpaceInformation> getAll() {
        return greenspaceRepo.findAll();
    }
    
}
