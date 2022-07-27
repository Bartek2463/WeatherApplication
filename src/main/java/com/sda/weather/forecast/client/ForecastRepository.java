package com.sda.weather.forecast.client;

import com.sda.weather.forecast.Forecast;

public interface ForecastRepository {
    Forecast save(Forecast forecast);
}
