package com.sda.weather.forecast;

import com.sda.weather.forecast.Forecast;
import com.sda.weather.location.Location;

import java.time.LocalDate;
import java.util.Optional;

public interface ForecastRepository {
    Forecast save(Forecast forecast);
    Optional<Forecast> findByLocation(Location location,LocalDate creationDate,LocalDate forecastDate);
}
