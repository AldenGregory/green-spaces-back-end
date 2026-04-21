package io.github.aldengregory.greenspaces.services;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.aldengregory.greenspaces.components.WeatherConverter;
import io.github.aldengregory.greenspaces.dtos.WeatherResponseDTO;
import io.github.aldengregory.greenspaces.dtos.WeatherResultDTO;

@Service
public class WeatherService {
    private final RestClient restClient;
    private final WeatherConverter responseConverter;

    /**
     * Creates a WeatherService instance and its associated RestClient.
     */
    public WeatherService(WeatherConverter responseConverter) {
        restClient = RestClient.builder().build();
        this.responseConverter = responseConverter;
    }

    public WeatherResultDTO requestWeather(double latitude, double longitude, String language) {
        // Build weather request.
        URI requestURI = UriComponentsBuilder.fromUriString("https://api.open-meteo.com/v1/forecast")
            .queryParam("latitude", latitude)
            .queryParam("longitude", longitude)
            .queryParam("temperature_unit", "fahrenheit")
            .queryParam("current", "temperature_2m,relative_humidity_2m,apparent_temperature")
            .queryParam("timezone", "America/Phoenix")
            .queryParam("daily", "sunrise,sunset")
            .queryParam("forecast_days", 1)
            .build()
            .toUri();

        // Make weather request.
        WeatherResponseDTO response = restClient.get()
            .uri(requestURI)
            .retrieve()
            .body(WeatherResponseDTO.class);

        // Convert response to WeathrResultDTO.
        return responseConverter.fromWeatherResponseDTO(response, language);
    }
}
