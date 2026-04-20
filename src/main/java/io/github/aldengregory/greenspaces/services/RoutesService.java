package io.github.aldengregory.greenspaces.services;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.aldengregory.greenspaces.components.RouteConverter;
import io.github.aldengregory.greenspaces.dtos.PathRequestDTO;
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
    @Value("${app.geoapify_api_key}")
    private String apiKey;
    private final RouteConverter responseConverter;
    private final RestClient restClient;
    public static final Set<String> supportedGeoapifyLangauges = Set.of("en", "es", "fr");

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
     * @param pathRequest A PathRequestDTO specifying source and destination
     *                    coordinates.
     * @return A JsonNode with Geoapify's response to the route request.
     */
    public RouteResultDTO requestRoute(PathRequestDTO pathRequest) {
        // Create string specifying standard and ending coordinates.
        String waypoints = String.format(
            "%.8f,%.8f|%.8f,%.8f",
            pathRequest.sourceLatitude(),
            pathRequest.sourceLongitude(),
            pathRequest.destinationLatitude(),
            pathRequest.destinationLongitude()
        );

        String neededLanguage = pathRequest.language();
        String geoapifyRequestLanguage = neededLanguage;

        // Use English for unsupported language requests.
        if (!supportedGeoapifyLangauges.contains(geoapifyRequestLanguage)) {
            geoapifyRequestLanguage = "en";
        }

        String units = "imperial";

        // Geoapify only provides imperial units in English.
        if (!"en".equals(neededLanguage)) {
            units = "metric";
        }

        // Build request URI.
        URI requestURI = UriComponentsBuilder.fromUriString("https://api.geoapify.com/v1/routing")
            .queryParam("waypoints", waypoints)
            .queryParam("mode", "transit")
            .queryParam("units", units)
            .queryParam("details", "instruction_details")
            .queryParam("lang", geoapifyRequestLanguage)
            .queryParam("apiKey", apiKey)
            .build()
            .toUri();

        // Send request.
        RouteResponseDTO response = restClient.get()
            .uri(requestURI)
            .retrieve()
            .body(RouteResponseDTO.class);

        return responseConverter.fromRouteResponseDTO(response, neededLanguage);
    } 
}
