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
    private Double latitude;
    private Double longitude;

    public Location(String city, String region, String country, Double latitude,Double longitude) {
        this.city = city;
        this.region = region;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
