package com.sda.weather.forecast.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ForecastClient {
    private static final String URL = "https://api.openweathermap.org/data/2.5/onecall?lat=%s&lon=%s&appid=a7f56c4235d1e36534313e315ea5e35a&exclude=current,minutely,hourly&units=metric";
    
}
