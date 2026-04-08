package io.github.aldengregory.greenspaces.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.aldengregory.greenspaces.dtos.PathRequestDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResultDTO;
import io.github.aldengregory.greenspaces.services.RoutesService;

/**
 * RoutesController handles requests related to route information.
 * 
 * This class retrives this information through a RoutesService which in turn
 * uses Geoapify.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RoutesController {
    private final RoutesService routesService;
    
    /**
     * Creates a RoutesController that uses a RoutesService to process
     * requests.
     * 
     * @param routesService The RoutesService instance this contorller will
     *                      use.
     */
    public RoutesController(RoutesService routesService) {
        this.routesService = routesService;
    }

    /**
     * Retrieves a route based on a start and ending destination.
     * 
     * @param path Specifies the start and end point for a route.
     * @return A description of a route from start to end using public transit.
     */
    @PostMapping("simple-route")
    public RouteResultDTO getRoute(@RequestBody PathRequestDTO path) {
        return routesService.requestRoute(path);
    }
}
