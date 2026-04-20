package io.github.aldengregory.greenspaces.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.aldengregory.greenspaces.dtos.ReachablePolygonResultDTO;
import io.github.aldengregory.greenspaces.services.ReachabilityService;

@RestController
public class ReachabilityController {

    private final ReachabilityService reachabilityService;

    public ReachabilityController(ReachabilityService reachabilityService) {
        this.reachabilityService = reachabilityService;
    }

    @GetMapping("/reachable-polygon")
    public ReachablePolygonResultDTO getReachablePolygon(
        @RequestParam double latitude,
        @RequestParam double longitude,
        @RequestParam int maxTravelMinutes
    ) {
        reachabilityService.getReachablePolygon(latitude, longitude, maxTravelMinutes);
        return reachabilityService.getReachablePolygon(latitude, longitude, maxTravelMinutes);
    }
}
