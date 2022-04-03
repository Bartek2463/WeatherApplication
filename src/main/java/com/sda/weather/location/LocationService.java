package com.sda.weather.location;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationService {

    private final LocationRepository locationRepository;
    private final ObjectMapper objectMapper;

    public LocationService(LocationRepository locationRepository, ObjectMapper objectMapper) {
        this.locationRepository = locationRepository;
        this.objectMapper = objectMapper;
    }

    Location createLocation(String city, String region, String country, Double longitude, Double latitude) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Miasto nie moze być puste ");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Kraj nie moze być pusty");
        }
        if (latitude > 90 || latitude < -90) {
            throw new IllegalArgumentException("Wartość szerokości poza przedziałem");
        }
        if (longitude > 180 || longitude < -90) {
            throw new IllegalArgumentException("Wartosc dlugosci  poza przedziałem");
        }

        Location location = new Location(city, region, country, longitude, latitude);
        Location savedLocation = locationRepository.save(location);
        return savedLocation;
    }

}
