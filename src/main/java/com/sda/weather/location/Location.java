package com.sda.weather.location;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String region;
    private String country;
    private Double longitude;
    private Double latitude;

    public Location(String city, String region, String country, Double longitude, Double latitude) {
        this.city = city;
        this.region = region;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
