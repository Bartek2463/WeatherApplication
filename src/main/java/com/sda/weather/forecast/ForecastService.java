package com.sda.weather.forecast;

import com.sda.weather.forecast.client.ForecastClient;
import com.sda.weather.location.Location;
import com.sda.weather.location.LocationService;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.hibernate.secure.spi.IntegrationException;

import java.time.LocalDate;
@RequiredArgsConstructor
public class ForecastService {

    private final LocationService locationService;
    private  final ForecastClient forecastClient;
    private  final ForecastRepository forecastRepository;

   Forecast getForecast(String lacationId,String period){
       //ternary opearator check value if sholud by integer
       period = (period==null||period.isBlank())?"1":period;
       int periodValue;
       try {
           periodValue = Integer.parseInt(period);
       }catch (NumberFormatException e){
           throw  new RuntimeException("Period Must by a number");
       }
       if (periodValue >5 || periodValue < 1) {
           throw  new RuntimeException("Period value muust by the range <1;5>");
       }
       Location location = locationService.getLocationByid(lacationId)
               .orElseThrow(() -> new RuntimeException("Location" + lacationId + "does not exists"));

       LocalDate now = LocalDate.now();
       LocalDate forecastDate = now.plusDays(periodValue);

       return forecastRepository.findByLocation(location,now,forecastDate)
               .orElseGet(()->generateForecast(location,forecastDate));
   }





    private Forecast generateForecast(Location location, LocalDate forecastDate){
        Forecast forecast = forecastClient.getForecast(location.getLatitude(), location.getLongitude(), forecastDate)
                .orElseThrow(() -> new InternalException("Forecast connot be genereted" + location.getCity()));
        forecast.setLocation(location);
        forecast.setCreationDate(LocalDate.now());
        return forecastRepository.save(forecast);
    }

}
