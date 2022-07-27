package com.sda.weather;

import com.sda.weather.location.LocationControler;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class UseInterfaces {

    private final Scanner scanner;
    private final LocationControler locationControler;


    public void run() {
        System.out.println("Aplikacja jest uruchomiona\n");
        while (true) {

            System.out.println("Witaj Serwisie Pogodowym, co chesz zrobić ?");
            System.out.println("1. Dodaj nowe dane ");
            System.out.println("2. Wyświetl dane pogodowe ");
            System.out.println("3. Pobierz Dane pogodowe ");
            System.out.println("0. Zamknij aplikacje ");

            int option = getInteger();
            switch (option) {
                case 1:
                    createLocation();
                    break;
                case 2:
                    getLocation();
                    break;
                case 3:
                    getWeatherForecast();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nie ma takiej opcji");
            }
        }
    }

    private void getWeatherForecast() {
        System.out.println("Podaj id lokalizacji: ");
        String cityId = scanner.nextLine();
        System.out.println("Podaj dzień prognozy [1-5] (opcjonalnie):");
        String period = scanner.nextLine();
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
        //POST : http://mojadomena.pl/location
        String requestbody = String.format("{\"city\":\"%s\",\"region\":\"%s\",\"country\":\"%s\",\"latitude\":\"%s\",\"longitude\":\"%s\"}",
                city, region, country, latitude, longitude);
        System.out.println("Wysłane Dane z strony uzytkownika " + requestbody);
        locationControler.createLocations(requestbody);

    }
    private  void getLocation(){
        String responseBody = locationControler.getAllLocations();
        responseBody = responseBody
                .replaceAll("\\{", "\n\t\\{")
                .replaceAll("}]", "}\n]");
        // GET: http://mojadomena.pl/locations
        System.out.println("Response Body of Server :\n" + responseBody + "\n");
    }

    private int getInteger() {
        while (true){
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            }catch (Exception e){
                System.out.println("\nValue must by have number : ");
            }
        }
    }
}
