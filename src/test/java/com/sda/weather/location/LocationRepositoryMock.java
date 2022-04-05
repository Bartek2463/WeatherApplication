package com.sda.weather.location;

import java.util.List;

public class LocationRepositoryMock implements LocationRepository{
    @Override
    public Location save(Location location) {
        location.setId(7L);
        return location;
    }

    @Override
    public List<Location> findAll() {
        return null;
    }
}
