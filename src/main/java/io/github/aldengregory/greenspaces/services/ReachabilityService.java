package io.github.aldengregory.greenspaces.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.aldengregory.greenspaces.components.ReachablePolygonConverter;
import io.github.aldengregory.greenspaces.dtos.ReachablePolygonResponseDTO;
import io.github.aldengregory.greenspaces.dtos.ReachablePolygonResultDTO;

@Service
public class ReachabilityService {
    @Value("${app.geoapify_api_key}")
    private String apiKey;

    private final RestClient restClient;
    private final ReachablePolygonConverter responseConverter;
    

    public ReachabilityService(ReachablePolygonConverter responseConverter) {
        restClient = RestClient.builder().build();

        this.responseConverter = responseConverter;
    }

    public ReachablePolygonResultDTO getReachablePolygon(
        double latitude,
        double longitude,
        int maxTravelMinutes
    ) {
        // API uses minutes rather than seconds.
        int maxTravelSeconds = maxTravelMinutes * 60;

        URI requestUri = UriComponentsBuilder.fromUriString("https://api.geoapify.com/v1/isoline")
            .queryParam("lat", latitude)
            .queryParam("lon", longitude)
            .queryParam("type", "time")
            .queryParam("mode", "transit")
            .queryParam("range", maxTravelSeconds)
            .queryParam("apiKey", apiKey)
            .build()
            .toUri();

        ReachablePolygonResponseDTO response = restClient.get()
            .uri(requestUri)
            .retrieve()
            .body(ReachablePolygonResponseDTO.class);

        return responseConverter.fromReachablePoylgonResponseDTO(response);
    }
}
