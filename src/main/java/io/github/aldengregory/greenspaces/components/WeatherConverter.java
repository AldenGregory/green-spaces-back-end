package io.github.aldengregory.greenspaces.components;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.stereotype.Component;

import io.github.aldengregory.greenspaces.dtos.WeatherResponseDTO;
import io.github.aldengregory.greenspaces.dtos.WeatherResultDTO;
import io.github.aldengregory.greenspaces.dtos.WeatherResultDTO.TemperatureDTO;

@Component
public class WeatherConverter {
    public WeatherResultDTO fromWeatherResponseDTO(WeatherResponseDTO response, String language) {
        // Access necessary information on weather.
        double temperature = response.current().temperature();
        double apparentTemperature = response.current().apparentTemperature();
        double relativeHumidity = response.current().relativeHumidity();

        // Access necessary units.
        String temperatureUnit = response.currentUnits().temperatureUnit();
        String apparentTemperatureUnit = response.currentUnits().apparentTemperatureUnit();

        // Construct temperatureDTOs.
        TemperatureDTO temperatureInfo = new TemperatureDTO(temperature, temperatureUnit);
        TemperatureDTO apparentTemperatureInfo = new TemperatureDTO(apparentTemperature, apparentTemperatureUnit);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
            .withLocale(Locale.of(language));

        // Build WeatherResultDTO.
        return new WeatherResultDTO(
            temperatureInfo, 
            apparentTemperatureInfo, 
            relativeHumidity,
            // These lists will only have one time for this program.
            response.daily().sunrise().get(0).format(timeFormatter),
            response.daily().sunset().get(0).format(timeFormatter)
        );
    }
}
