package com.sda.weather;

import java.sql.SQLOutput;
import java.util.Scanner;

public class UseInterfaces {

           public void run(){
               System.out.println("Aplikacja jest uruchomiona\n");
               Scanner scanner = new Scanner(System.in);


               while (true){

                   System.out.println("Witaj Serwisie Pogodowym, co chesz zrobić ?");
                   System.out.println("1. Dodać nowe dane ");
                   System.out.println("0. Zamknąć aplikacje");

                   int option = scanner.nextInt();
                   switch (option){
                       case 1:
                           createLocation();
                           break;
                       case 0:
                           return;
                       default:
                           System.out.println("Nie ma takiej opcji");
                   }

               }


           }

           private void createLocation(){
               Scanner scanner = new Scanner(System.in);
               System.out.println("Podaj nazwe miasta ");
               String city = scanner.nextLine();
               System.out.println("Podaj dlugosc geograficzna");
               Double longitude = scanner.nextDouble();
               System.out.println("Podaj szerokosc geograficzną");
               Double latitude = scanner.nextDouble();
               System.out.println("Podaj region");
               String region = scanner.nextLine();
               System.out.println("Podaj Nazwe kraju");
               String country = scanner.nextLine();

           }



}
