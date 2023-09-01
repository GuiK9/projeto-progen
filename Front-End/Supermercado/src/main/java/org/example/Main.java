package org.example;
import org.example.fetch.Client;


public class Main {
    public static void main(String[] args) {

        Client cliente = new Client();

        String jsonString = """
                {
                "descrição": "Produtotop",
                "marca": "marquinha",
                "preco_custo": 12.50,
                "pre_venda": 15.50,
                "codigo_barra": "12342132",
                "embalagem": "cx"
                }
                """;

        String products = cliente.updateProduct("http://localhost:5000/produto/38", jsonString);
        System.out.println(products);

    }
}