package com.example.supermercado;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import static com.example.supermercado.Fetch.Client.*;

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
    private String selectedId;

    public void updateProduct() throws JsonProcessingException {
        updateProducts(selectedId, parseJsonString());
        tableInitialize();
    }

    public void deleteProduct() throws JsonProcessingException {
        deleteProducts(selectedId);
        tableInitialize();
    }


    public void insertProduct(ActionEvent e) throws JsonProcessingException {
        String jsonString = parseJsonString();
        insertProducts(jsonString);
        tableInitialize();
    }

    @FXML
    private void initialize() throws JsonProcessingException {
        tableInitialize();
        choiceBoxInitializer();
        TableSelector();
    }

    private void choiceBoxInitializer() {
        ObservableList<String> itemsEmbalagem = FXCollections.observableList(List.of("cx - caixa", "un - unidade", "pt - pacote", "fd - farco", "pc - peça", "lt - litro", "fr - frasco", "ev - envelope"));
        ChoiceEmbalagem.setValue("cx - caixa");
        ChoiceEmbalagem.setItems(itemsEmbalagem);
    }

    private void tableInitialize() throws JsonProcessingException {


        String jsonString = getProducts();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        ObservableList<Produto> dados = FXCollections.observableArrayList();

        for (JsonNode objectNode : jsonNode) {

            String id = objectNode.get("id").toString().replaceAll("^\"|\"$", "");
            Double precoCusto = Double.parseDouble(objectNode.get("Preço de custo").toString().replaceAll("^\"|\"$", ""));
            String embalagem = objectNode.get("Embalagem").toString().replaceAll("^\"|\"$", "");
            String descricao = objectNode.get("Descrição").toString().replaceAll("^\"|\"$", "");
            String marca = objectNode.get("Marca").toString().replaceAll("^\"|\"$", "");
            String codigoDeBarras = objectNode.get("Codigo de Barras").toString().replaceAll("^\"|\"$", "");
            double precoDeVenda = Double.parseDouble(objectNode.get("Preço de venda").toString().replaceAll("^\"|\"$", ""));

            Produto produto = new Produto(descricao, marca, codigoDeBarras, precoDeVenda, id, precoCusto, embalagem);

            ColumnDescrcao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            ColumnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
            ColumnCodBarras.setCellValueFactory(new PropertyValueFactory<>("codigoDeBarras"));
            ColumnPrecVendas.setCellValueFactory(new PropertyValueFactory<>("precoDeVenda"));

            dados.add(produto);
        }

        TableProdutos.setItems(dados);
    }

    private void TableSelector() {
        TableProdutos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                TextDescricao.setText(newValue.getDescricao());
                TextMarca.setText(newValue.getMarca());
                TextPrecCusto.setText(String.valueOf(newValue.getPrecoCusto()));
                TextPrecVenda.setText(String.valueOf(newValue.getPrecoDeVenda()));
                TextCodBarras.setText(newValue.getCodigoDeBarras());
                ChoiceEmbalagem.setValue(newValue.getEmbalagem());
                selectedId(newValue.getId());
            }
        });
    }

    private void selectedId(String id) {
        selectedId = id;
    }

    private String parseJsonString() throws JsonProcessingException {
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

        return objectMapper.writeValueAsString(jsonMap);
    }

}