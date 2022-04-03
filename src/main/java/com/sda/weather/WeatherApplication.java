package com.sda.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.location.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class WeatherApplication {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();


        ObjectMapper objectMapper = new ObjectMapper();
        LocationRepository locationRepository = new LocationRepositoryImpl(sessionFactory);
        LocationService locationService = new LocationService(locationRepository, objectMapper);
        LocationControler locationControler = new LocationControler(objectMapper, locationService);
        UseInterfaces useInterfaces = new UseInterfaces(locationControler);


        useInterfaces.run();
    }
}
