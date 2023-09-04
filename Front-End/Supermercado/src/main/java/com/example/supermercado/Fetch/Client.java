package com.example.supermercado.Fetch;

import org.apache.commons.lang3.StringEscapeUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Client {

    static String url = "http://localhost:5000";
    static HttpClient client = HttpClient.newHttpClient();

    public static String getProducts() {
        HttpRequest request = generateRequest(url).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return StringEscapeUtils.unescapeJava(response.body());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String insertProducts(String url, String produtoJSON) {
        HttpRequest request = generateRequest(url).POST(HttpRequest.BodyPublishers.ofString(produtoJSON)).header("Content-Type", "application/json").build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String updateProduct(String url, String produtoJSON){
        HttpRequest request = generateRequest(url).PUT(HttpRequest.BodyPublishers.ofString(produtoJSON)).header("Content-Type", "application/json").build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        } catch (Exception e) {
            throw new  RuntimeException(e);
        }
    }

    public String deleteProduct(String url){
        HttpRequest request = generateRequest(url).DELETE().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest.Builder generateRequest(String url){
        return HttpRequest.newBuilder().uri(URI.create(url)).header("Accept-Charset", StandardCharsets.UTF_8.name());
    }
}