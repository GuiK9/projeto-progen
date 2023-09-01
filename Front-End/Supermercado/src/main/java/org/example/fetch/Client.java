package org.example.fetch;

import org.apache.commons.lang3.StringEscapeUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;



public class Client {

    HttpClient cliente = HttpClient.newHttpClient();

    public String getProducts(String url) {
        HttpRequest request = generateRequest(url);
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            return StringEscapeUtils.unescapeJava(response.body());
        } catch (Exception e){
           return (e.getMessage());
        }
    }

    public String insertProducts(String url, String produtoJSON) {
        String produtJson =  produtoJSON;
        return "s";
    }

    private HttpRequest generateRequest(String url){
        return HttpRequest.newBuilder().uri(URI.create(url)).header("Accept-Charset", StandardCharsets.UTF_8.name()).build();
    }
}
