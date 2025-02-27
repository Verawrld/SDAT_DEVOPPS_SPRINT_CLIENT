package com.example;

import com.example.models.Airport;
import com.example.models.Passenger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiClientTest {
    private MockWebServer mockWebServer;
    private ApiClient apiClient;
    private Gson gson = new Gson();

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        apiClient = new ApiClient() {
            @Override
            protected String getBaseUrl() {
                return mockWebServer.url("/api").toString();
            }
        };
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testGetAirportsInCity() throws IOException {
        List<Airport> expectedAirports = List.of(new Airport());
        mockWebServer.enqueue(new MockResponse()
                .setBody(gson.toJson(expectedAirports))
                .addHeader("Content-Type", "application/json"));

        List<Airport> response = apiClient.getAirportsInCity("New York");
        assertNotNull(response);
        assertEquals(expectedAirports.size(), response.size());
    }

    @Test
    public void testGetAircraftPassengers() throws IOException {
        List<Passenger> expectedPassengers = List.of(new Passenger());
        mockWebServer.enqueue(new MockResponse()
                .setBody(gson.toJson(expectedPassengers))
                .addHeader("Content-Type", "application/json"));

        List<Passenger> response = apiClient.getAircraftPassengers("1");
        assertNotNull(response);
        assertEquals(expectedPassengers.size(), response.size());
    }

    @Test
    public void testGetAircraftTakeOffAndLand() throws IOException {
        List<Airport> expectedAirports = List.of(new Airport());
        mockWebServer.enqueue(new MockResponse()
                .setBody(gson.toJson(expectedAirports))
                .addHeader("Content-Type", "application/json"));

        List<Airport> response = apiClient.getAircraftTakeOffAndLand("1");
        assertNotNull(response);
        assertEquals(expectedAirports.size(), response.size());
    }

    @Test
    public void testGetAirportsUsedByPassenger() throws IOException {
        List<Airport> expectedAirports = List.of(new Airport());
        mockWebServer.enqueue(new MockResponse()
                .setBody(gson.toJson(expectedAirports))
                .addHeader("Content-Type", "application/json"));

        List<Airport> response = apiClient.getAirportsUsedByPassenger("1");
        assertNotNull(response);
        assertEquals(expectedAirports.size(), response.size());
    }
}
