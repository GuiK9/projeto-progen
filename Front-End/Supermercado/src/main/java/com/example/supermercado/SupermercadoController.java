package com.example.supermercado;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static com.example.supermercado.Fetch.Client.getProducts;

public class SupermercadoController {

    @FXML
    private ChoiceBox<String> ChoiceEmbalagem;
    ObservableList<String> itemsEmbalagem = FXCollections.observableList(List.of("cx - caixa", "un - unidade", "pt - pacote", "fd - farco", "pc - peça", "lt - litro", "fr - frasco", "ev - envelope"));
    @FXML
    private TableColumn<String, String> ColumnCodBarras;
    @FXML
    private TableColumn<String, String> ColumnDescrcao;
    @FXML
    private TableColumn<String, String> ColumnMarca;
    @FXML
    private TableColumn<String, String> ColumnPrecVendas;
    @FXML
    private TableView<?> TableProdutos;
    @FXML
    private TextField TextCodBarras;
    @FXML
    private TextField TextDescricao;
    @FXML
    private TextField TextMarca;
    @FXML
    private TextField TextPrecCusto;
    @FXML
    private TextField TextPrecVenda;
    @FXML
    private Button AddButton;

    public void insertProduct(ActionEvent e){
        String text = getProducts();
        System.out.println(text);
    }

    @FXML
    private void initialize() throws JsonProcessingException {
        String jsonString = getProducts();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        for (JsonNode objectNode : jsonNode) {
            JsonNode descricao = objectNode.get("Descrição");
            JsonNode marca = objectNode.get("Marca");
            JsonNode codigoDeBarras = objectNode.get("Codigo de barras");
            JsonNode precoDeVenda = objectNode.get("Preço de venda");
        }

        ChoiceEmbalagem.setValue("cx");
        ChoiceEmbalagem.setItems(itemsEmbalagem);
    }

}