package com.example;

import com.example.models.Airport;
import com.example.models.Passenger;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Get airports in a city");
                System.out.println("2. List all aircraft passengers have travelled on");
                System.out.println("3. Get airports aircraft can take off from and land at");
                System.out.println("4. Get airports passengers have used");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter city name: ");
                            String cityName = scanner.nextLine();
                            List<Airport> airports = apiClient.getAirportsInCity(cityName);
                            airports.forEach(airport -> System.out.println(airport));
                            break;
                        case 2:
                            System.out.print("Enter aircraft ID: ");
                            String aircraftId = scanner.nextLine();
                            List<Passenger> passengers = apiClient.getAircraftPassengers(aircraftId);
                            passengers.forEach(passenger -> System.out.println(passenger));
                            break;
                        case 3:
                            System.out.print("Enter aircraft ID: ");
                            String aircraftId2 = scanner.nextLine();
                            List<Airport> airports2 = apiClient.getAircraftTakeOffAndLand(aircraftId2);
                            airports2.forEach(airport -> System.out.println(airport));
                            break;
                        case 4:
                            System.out.print("Enter passenger ID: ");
                            String passengerId = scanner.nextLine();
                            List<Airport> airports3 = apiClient.getAirportsUsedByPassenger(passengerId);
                            airports3.forEach(airport -> System.out.println(airport));
                            break;
                        case 5:
                            System.out.println("Exiting...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            }
        }
    }
}
