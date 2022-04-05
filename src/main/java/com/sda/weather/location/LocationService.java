package com.sda.weather.location;

import antlr.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static antlr.StringUtils.*;
import static org.hibernate.hql.internal.classic.ParserHelper.isWhitespace;

public class LocationService {

    private final LocationRepository locationRepository;
    private final ObjectMapper objectMapper;

    public LocationService(LocationRepository locationRepository, ObjectMapper objectMapper) {
        this.locationRepository = locationRepository;
        this.objectMapper = objectMapper;
    }

    Location createLocation(String city, String region, String country, Double latitude, Double longitude) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Miasto nie moze być puste ");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Kraj nie moze być pusty");
        }
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("Wartość szerokości poza przedziałem");
        }
        if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Wartosc dlugosci  poza przedziałem");
        }
        if (region != null && !region.isBlank()) {
        } else if (region.isEmpty()) {
        } else {
            throw new IllegalArgumentException("Wprowadzona Wartość jest nieprwidłowa");
        }

        Location location = new Location(city, region, country, longitude, latitude);
        Location savedLocation = locationRepository.save(location);
        return savedLocation;
    }

    List<Location> getLocations() {
        List<Location> locations = locationRepository.findAll();

        return locations;
    }

}
