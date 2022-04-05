package com.sda.weather.location;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LocationServiceTest {

    LocationService locationService;

    @BeforeEach
    void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        ObjectMapper objectMapper = new ObjectMapper();
        locationService = new LocationService(locationRepository, objectMapper);

    }

    @DisplayName("Data_Proper")
    @Test
    void createLocation_whenDataIsProper_returnsNewObject() {
        Location result = locationService.createLocation("Warszawa", "Europa", "Polska", 0.0, 0.0);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getCity()).isNotNull();
        assertThat(result.getCountry()).isNotNull();
        assertThat(result.getLatitude()).isNotNull();
        assertThat(result.getRegion()).isNotNull();
        assertThat(result.getRegion()).isNotEmpty();
    }

    @DisplayName("Latitude_minminus")
    @Test
    void createLocation_whenLatitude_S_MinMinus() {
        Throwable result = catchThrowable(() -> locationService.createLocation("Warszawa", "Europa", "Polska", -91.0, 0.0));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Latitude_max")
    @Test
    void createLocation_whenLatitude_N_MaxPlus() {
        Throwable result = catchThrowable(() -> locationService.createLocation("Warszawa", "Europa", "Polska", 91.0, 0.0));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Longitude_min")
    @Test
    void createLocation_whenLongitude_W_MinMinus() {
        Throwable result = catchThrowable(() -> locationService.createLocation("Warszawa", "Europa", "Polska", 0.0, -181.0));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Longitude_max")
    @Test
    void createLocation_whenLongitude_E_MaxPlus() {
        Throwable result = catchThrowable(() -> locationService.createLocation("Warszawa", "Europa", "Polska", 0.0, 181.0));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("City_is_empty")
    @Test
    void createLocation_whenCityIsEmpty_throwsAnExcepiton() {

        Throwable result = catchThrowable(() -> locationService.createLocation("", "europa", "Polska", 0.0, 0.0));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Country_is_empty")
    @Test
    void creationLocation_whenCountryisEmpty_throwsAnException() {
        Throwable result = catchThrowable(() -> locationService.createLocation("Warszawa", "", "", 43.0, 34.0));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("Region_is_empty")
    @Test
    void createLocation_whenRegionIsEmpty() {
        Location result = locationService.createLocation("Warszawa", "", "Polska", 90.0, 80.0);
        assertThat(result.getRegion()).isEmpty();
    }

    @DisplayName("Region_is_blank")
    @Test
    void createLocation_whenRegionIsBlank() {
        Throwable result = catchThrowable(() -> locationService.createLocation("Warszawa", "                       ", "Polska", 90.0, 80.0));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);

    }

}
