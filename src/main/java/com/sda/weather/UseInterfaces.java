package com.sda.weather;

import com.sda.weather.location.LocationControler;

import java.util.Scanner;

public class UseInterfaces {

    private final LocationControler locationControler;

    public UseInterfaces(LocationControler locationControler) {
        this.locationControler = locationControler;
    }

    public void run() {
        System.out.println("Aplikacja jest uruchomiona\n");
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Witaj Serwisie Pogodowym, co chesz zrobić ?");
            System.out.println("1. Dodaj nowe dane ");
            System.out.println("2. Wyświetl dane pogodowe ");
            System.out.println("3. Pobierz Dane pogodowe ");
            System.out.println("0. Zamknij aplikacje ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    createLocation();
                    break;
                case 2:
                    showLocation();
                case 3:
                    loadLocation();
                case 0:
                    return;
                default:
                    System.out.println("Nie ma takiej opcji");
            }
        }
    }

    private void createLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe miasta ");
        String city = scanner.nextLine();
        System.out.println("Podaj region");
        String region = scanner.nextLine();
        System.out.println("Podaj Nazwe kraju");
        String country = scanner.nextLine();
        System.out.println("Podaj szerokosc geograficzną");
        Double latitude = scanner.nextDouble();
        System.out.println("Podaj dlugosc geograficzna");
        Double longitude = scanner.nextDouble();
        String requestbody = String.format("{\"city\":\"%s\",\"region\":\"%s\",\"country\":\"%s\",\"latitude\":\"%s\",\"longitude\":\"%s\"}",
                city, region, country, latitude, longitude);
        System.out.println("Wysłane Dane z strony uzytkownika " + requestbody);
        locationControler.createLocations(requestbody);

    }

    private void showLocation() {
        String allLocations = locationControler.getAllLocations();
        System.out.println("Odebrany http response: "+ allLocations);

    }

    private void loadLocation() {


    }
}
