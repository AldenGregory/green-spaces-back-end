package io.github.aldengregory.greenspaces.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public record WeatherResponseDTO(
    @JsonAlias("current_units") WeatherUnitsDTO currentUnits,
    CurrentWeatherDTO current,
    DailyWeatherDTO daily
) {
    public record WeatherUnitsDTO(
        @JsonAlias("temperature_2m") String temperatureUnit,
        @JsonAlias("apparent_temperature") String apparentTemperatureUnit
    ) {}

    public record CurrentWeatherDTO(
        @JsonAlias("temperature_2m") double temperature,
        @JsonAlias("relative_humidity_2m") double relativeHumidity,
        @JsonAlias("apparent_temperature") double apparentTemperature
    ) {}

    public record DailyWeatherDTO(
        List<LocalDateTime> sunrise,
        List<LocalDateTime> sunset
    ) {}
}
