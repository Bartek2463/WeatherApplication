package com.sda.weather.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationServiceTest {

    LocationService locationService;

    @BeforeEach
    void setUp(){
        LocationRepository locationRepository = new LocationRepositoryMock();
        ObjectMapper objectMapper = new ObjectMapper();
        locationService= new LocationService(locationRepository,objectMapper);

    }
@Test
    void createLocation_whenDataIsProper_returnsNewObject(){
        Location result = locationService.createLocation("Rzeszów","","Polska",100.0,44.7);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCity()).isNotNull();
        assertThat(result.getCountry()).isNotNull();
        assertThat(result.getRegion()).isNotNull();
        assertThat(result.getLongitude()>100.0);
        assertThat(result.getLatitude()>180);
    }


}
