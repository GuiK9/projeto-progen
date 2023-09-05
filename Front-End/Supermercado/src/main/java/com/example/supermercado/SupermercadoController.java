package com.example.supermercado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.supermercado.Models.Produto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;

import static com.example.supermercado.Fetch.Client.getProducts;
import static com.example.supermercado.Fetch.Client.insertProducts;

public class SupermercadoController {

    @FXML
    private ChoiceBox<String> ChoiceEmbalagem;
    @FXML
    private TableColumn<Produto, String> ColumnCodBarras;
    @FXML
    private TableColumn<Produto, String> ColumnDescrcao;
    @FXML
    private TableColumn<Produto, String> ColumnMarca;
    @FXML
    private TableColumn<Produto, Double> ColumnPrecVendas;
    @FXML
    private TableView<Produto> TableProdutos;
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

    public void insertProduct(ActionEvent e) throws JsonProcessingException {

        String embalagemFormated = ChoiceEmbalagem.getValue().substring(0, 2);

        Produto produto = new Produto(TextDescricao.getText(), TextMarca.getText(), Double.parseDouble(TextPrecCusto.getText()), Double.parseDouble(TextPrecVenda.getText()), TextCodBarras.getText(), embalagemFormated);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("descricao", produto.getDescricao());
        jsonMap.put("marca", produto.getMarca());
        jsonMap.put("preco_custo", produto.getPrecoCusto());
        jsonMap.put("pre_venda", produto.getPrecoDeVenda());
        jsonMap.put("codigo_barra", produto.getCodigoDeBarras());
        jsonMap.put("embalagem", produto.getEmbalagem());

        try {
            String jsonString = objectMapper.writeValueAsString(jsonMap);
            insertProducts(jsonString);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        tableInitialize();
    }

    @FXML
    private void initialize() throws JsonProcessingException {
        tableInitialize();
        choiceBoxInitializer();
    }

    private void choiceBoxInitializer() {
        ObservableList<String> itemsEmbalagem = FXCollections.observableList(List.of("cx - caixa", "un - unidade", "pt - pacote", "fd - farco", "pc - peça", "lt - litro", "fr - frasco", "ev - envelope"));
        ChoiceEmbalagem.setValue("cx");
        ChoiceEmbalagem.setItems(itemsEmbalagem);
    }

    private void tableInitialize() throws JsonProcessingException {
        String jsonString = getProducts();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        ObservableList<Produto> dados = FXCollections.observableArrayList();

        for (JsonNode objectNode : jsonNode) {

            String descricao = objectNode.get("Descrição").toString().replaceAll("^\"|\"$", "");
            String marca = objectNode.get("Marca").toString().replaceAll("^\"|\"$", "");
            String codigoDeBarras = objectNode.get("Codigo de Barras").toString().replaceAll("^\"|\"$", "");
            double precoDeVenda = Double.parseDouble(objectNode.get("Preço de venda").toString().replaceAll("^\"|\"$", ""));

            Produto produto = new Produto(descricao, marca, codigoDeBarras, precoDeVenda);

            ColumnDescrcao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            ColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            ColumnCodBarras.setCellValueFactory(new PropertyValueFactory<>("codigoDeBarras"));
            ColumnPrecVendas.setCellValueFactory(new PropertyValueFactory<>("precoDeVenda"));

            dados.add(produto);
        }

        //TableProdutos.getColumns().addAll(ColumnDescrcao, ColumnMarca, ColumnCodBarras, ColumnPrecVendas);
        TableProdutos.setItems(dados);
    }

}