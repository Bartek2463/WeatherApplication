package com.sda.weather.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class LocationControler {

    private final ObjectMapper objectMapper;
    private final LocationService locationService;

    public LocationControler(ObjectMapper objectMapper, LocationService locationService) {
        this.objectMapper = objectMapper;
        this.locationService = locationService;
    }

    public String createLocations(String json) {
        try {
            LocationDTO requestbBody = objectMapper.readValue(json, LocationDTO.class);
            String city = requestbBody.getCity();
            String region = requestbBody.getRegion();
            String country = requestbBody.getCountry();
            Double latitude = requestbBody.getLatitude();
            Double longitude = requestbBody.getLongitude();
            Location savedLocation = locationService.createLocation(city, region, country, latitude, longitude);
            LocationDTO locationDTO = maptoLocationDTO(savedLocation);
            String serializedLocationDTO = objectMapper.writeValueAsString(locationDTO);
            return serializedLocationDTO;
        } catch (Exception e) {
            return String.format("{\"errorMessage\":\"%s\"}", e.getMessage());
        }
    }

    public String getAllLocations() {
        try {
            List<Location> locations = locationService.getLocations();
            List<LocationDTO> locationDTOList = locations.stream()
                    .map(this::maptoLocationDTO)
                    .collect(Collectors.toList());
            String s = objectMapper.writeValueAsString(locationDTOList);
            return s;
        } catch (JsonProcessingException e) {
            return String.format("{\"message", "something not work\"}", e.getMessage());
        }
    }

    private LocationDTO maptoLocationDTO(Location savedLocation) {

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(savedLocation.getId());
        locationDTO.setCity(savedLocation.getCity());
        locationDTO.setRegion(savedLocation.getRegion());
        locationDTO.setCountry(savedLocation.getCountry());
        locationDTO.setLatitude(savedLocation.getLatitude());
        locationDTO.setLongitude(savedLocation.getLongitude());
        return locationDTO;
    }
}
