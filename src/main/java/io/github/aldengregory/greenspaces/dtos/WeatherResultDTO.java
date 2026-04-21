package io.github.aldengregory.greenspaces.dtos;

public record WeatherResultDTO(
    TemperatureDTO temperature,
    TemperatureDTO apparentTemperature,
    double relativeHumidity,
    String sunrise,
    String sunset
) {
    public record TemperatureDTO(
        double temperature,
        String unit
    ) {}
}
