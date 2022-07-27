package com.sda.weather.forecast.client;

import com.sda.weather.forecast.Forecast;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ForecastResponseMapper {

    public Forecast asForecast(ForecastResponse.Dailyforecast dailyforecast){
        LocalDate forecastDate = asLocalDate(dailyforecast.getTimestamp());
        Forecast forecast = new Forecast();
        forecast.setHumidity(dailyforecast.getHumidity());
        forecast.setPressure(dailyforecast.getPressure());
        forecast.setTemperature(dailyforecast.getTemperature().getDay());
        forecast.setWindSpeed(dailyforecast.getWindSpeed());
        forecast.setForecastDate(forecastDate);
        return forecast;
    }

    LocalDate asLocalDate(Long timestampValue){
        Timestamp timestamp = new Timestamp(timestampValue*1000);
        return timestamp.toLocalDateTime().toLocalDate();
    }

}
