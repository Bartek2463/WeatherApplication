package com.sda.weather.location;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationControler {

    private final ObjectMapper objectMapper;
    private final LocationService locationService;

    public LocationControler(ObjectMapper objectMapper, LocationService locationService) {
        this.objectMapper = objectMapper;
        this.locationService = locationService;
    }

    public String createLocation(String json) {
        try {
            LocationDTO requestbBody = objectMapper.readValue(json, LocationDTO.class);
            String city = requestbBody.getCity();
            String region = requestbBody.getRegion();
            String country = requestbBody.getCountry();
            Double longitude = requestbBody.getLongitude();
            Double latitude = requestbBody.getLatitude();
            Location savedLocation = locationService.createLocation(city, region, country, longitude, latitude);
            LocationDTO locationDTO = maptoLocationDTO(savedLocation);
            String serializedLocationDTO = objectMapper.writeValueAsString(locationDTO);
            return serializedLocationDTO;
        } catch (Exception e) {
            return String.format("{\"errorMessage\":\"%s\"}", e.getMessage());
        }
    }

    private LocationDTO maptoLocationDTO(Location savedLocation) {

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(savedLocation.getId());
        locationDTO.setCity(savedLocation.getCity());
        locationDTO.setRegion(savedLocation.getRegion());
        locationDTO.setCountry(savedLocation.getCountry());
        locationDTO.setLongitude(savedLocation.getLongitude());
        locationDTO.setLatitude(savedLocation.getLatitude());
        return locationDTO;

    }
}
