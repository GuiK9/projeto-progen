package org.example;
import org.example.fetch.Client;
public class Main {
    public static void main(String[] args) {

        Client cliente = new Client();

        String products = cliente.getProducts("http://localhost:5000");
        System.out.println(products);

    }
}