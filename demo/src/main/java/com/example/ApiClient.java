package com.example;

import com.example.models.Airport;
import com.example.models.Passenger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiClient {
    private static final String BASE_URL = "http://localhost:8080/api";
    private Gson gson = new Gson();

    protected String getBaseUrl() {
        return BASE_URL;
    }

    public List<Airport> getAirportsInCity(String cityName) throws IOException {
        String encodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8.toString());
        String url = getBaseUrl() + "/airports?city=" + encodedCityName;
        String jsonResponse = sendGetRequest(url);
        Type listType = new TypeToken<List<Airport>>() {
        }.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public List<Passenger> getAircraftPassengers(String aircraftId) throws IOException {
        String url = getBaseUrl() + "/aircraft/" + aircraftId + "/passengers";
        String jsonResponse = sendGetRequest(url);
        Type listType = new TypeToken<List<Passenger>>() {
        }.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public List<Airport> getAircraftTakeOffAndLand(String aircraftId) throws IOException {
        String url = getBaseUrl() + "/aircraft/" + aircraftId + "/airports";
        String jsonResponse = sendGetRequest(url);
        Type listType = new TypeToken<List<Airport>>() {
        }.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public List<Airport> getAirportsUsedByPassenger(String passengerId) throws IOException {
        String url = getBaseUrl() + "/passengers/" + passengerId + "/airports";
        String jsonResponse = sendGetRequest(url);
        Type listType = new TypeToken<List<Airport>>() {
        }.getType();
        return gson.fromJson(jsonResponse, listType);
    }

    private String sendGetRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            return EntityUtils.toString(response.getEntity());
        } else {
            throw new IOException("Error: " + statusCode + " - " + response.getStatusLine().getReasonPhrase());
        }
    }
}
