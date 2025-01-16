package com.alura.challengeAlura.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

public class ApiRequest {
    
    public static String bookApiRequest(String data) {
        
        String dataFormatted = URLEncoder.encode(data, StandardCharsets.UTF_8);
        
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://gutendex.com/books/?search=" + dataFormatted))
                .build();
            HttpResponse<String> response = client
                .send(request, BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
