package io.github.aldengregory.greenspaces.controllers;

import org.springframework.web.bind.annotation.RestController;

import io.github.aldengregory.greenspaces.dtos.WeatherResultDTO;
import io.github.aldengregory.greenspaces.services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("weather")
    public WeatherResultDTO getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.requestWeather(latitude, longitude);
    }
    
}
