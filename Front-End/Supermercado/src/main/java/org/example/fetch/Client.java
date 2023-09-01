package org.example.fetch;

import org.apache.commons.lang3.StringEscapeUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Client {

    HttpClient client = HttpClient.newHttpClient();

    public String getProducts(String url) {
        HttpRequest request = generateRequest(url).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return StringEscapeUtils.unescapeJava(response.body());
        } catch (Exception e){
           return (e.getMessage());
        }
    }

    public String insertProducts(String url, String produtoJSON) {
        String produtJson =  produtoJSON;
        HttpRequest request = generateRequest(url).POST(HttpRequest.BodyPublishers.ofString(produtJson)).header("Content-Type", "application/json").build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            System.out.println(produtJson);
            return response.body();
        }catch (Exception e) {
            return(e.getMessage());
        }
    }

    private HttpRequest.Builder generateRequest(String url){
        return HttpRequest.newBuilder().uri(URI.create(url)).header("Accept-Charset", StandardCharsets.UTF_8.name());
    }
}
