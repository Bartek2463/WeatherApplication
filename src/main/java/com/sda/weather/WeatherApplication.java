package com.sda.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.location.*;
import com.sda.weather.location.LocationControler;
import com.sda.weather.location.LocationRepository;
import com.sda.weather.location.LocationRepositoryImpl;
import com.sda.weather.location.LocationService;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Scanner;


public class WeatherApplication {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
        LocationRepository locationRepository = new LocationRepositoryImpl(sessionFactory);
        LocationService locationService = new LocationService(locationRepository);
        LocationControler locationControler = new LocationControler(objectMapper, locationService);
        Scanner scanner = new Scanner(System.in);
        UseInterfaces useInterfaces = new UseInterfaces(scanner,locationControler);


        useInterfaces.run();

    }
}
