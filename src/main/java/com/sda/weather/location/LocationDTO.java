package com.sda.weather.location;

import lombok.Data;

@Data
public class LocationDTO {
    private Long id;
    private String city;
    private String region;
    private String country;
    private Double longitude;
    private Double latitude;
}