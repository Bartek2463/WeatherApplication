package com.sda.weather.location;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double latitude, Double longitude) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City value cannot be null ");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country value cannot be null");
        }
        if (longitude == null) {
            throw new IllegalArgumentException("Longitude value cannot be null ");
        }
        if (latitude == null) {
            throw new IllegalArgumentException(("Latitude value cannot be null"));
        }

        if (longitude > 90.0 || longitude < -90.0) {
            throw new IllegalArgumentException("Value longitude must by in interval  ");
        }
        if (latitude > 180.0 || longitude < -180.0) {
            throw new IllegalArgumentException("Value latitude must by in interval");
        }
        if (region != null && region.isBlank()){
            region = null;
        }
        Location location = new Location(city, region, country, longitude, latitude);
        return locationRepository.save(location);
    }

    List<Location> getLocations() {
        return locationRepository.findAll();
    }
    public Optional<Location> getLocationByid(String cityId) {
        try {
            long id = Long.parseLong(cityId);
            return locationRepository.getLocationByid(id);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
