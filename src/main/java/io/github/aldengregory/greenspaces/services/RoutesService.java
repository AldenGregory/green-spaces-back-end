package io.github.aldengregory.greenspaces.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.aldengregory.greenspaces.components.RouteConverter;
import io.github.aldengregory.greenspaces.dtos.PathDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResponseDTO;
import io.github.aldengregory.greenspaces.dtos.RouteResultDTO;

/**
 * RoutesService handles logic and makes requests associated with routes.
 * 
 * This class does not access a database. Instead, it makes requests to the
 * Geoapify API. It is used by the RoutesController class to process requests.
 */
@Service
public class RoutesService {
    @Value("${API_KEY}")
    private String apiKey;
    private final RouteConverter responseConverter;
    private final RestClient restClient;

    /**
     * Creates a RoutesService instance and its associated RestClient.
     */
    public RoutesService(RouteConverter responseConverter) {
        this.responseConverter = responseConverter;
        restClient = RestClient.builder().build();
    }

    /**
     * Sends a request for a route from source to destination coordinates.
     * 
     * The route this method finds uses public transportation. Results use
     * imperial units. This request is made to Geoapify.
     * 
     * @param path A PathDTO specifying source and destination coordinates.
     * @return A JsonNode with Geoapify's response to the route request.
     */
    public RouteResultDTO requestRoute(PathDTO path) {
        // Create string specifying standard and ending coordinates.
        String waypoints = String.format(
            "%.8f,%.8f|%.8f,%.8f",
            path.sourceLatitude(),
            path.sourceLongitude(),
            path.destinationLatitude(),
            path.destinationLongitude()
        );

        // Build request URI.
        URI requestURI = UriComponentsBuilder.fromUriString("https://api.geoapify.com/v1/routing")
            .queryParam("waypoints", waypoints)
            .queryParam("mode", "transit")
            .queryParam("units", "imperial")
            .queryParam("details", "instruction_details")
            .queryParam("apiKey", apiKey)
            .build()
            .toUri();

        System.out.println(requestURI);

        // Send request.
        RouteResponseDTO response = restClient.get()
            .uri(requestURI)
            .retrieve()
            .body(RouteResponseDTO.class);

        return responseConverter.fromRouteResponseDTO(response);
    } 
}
