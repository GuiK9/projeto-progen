package com.example.supermercado.Models;

public class Produto {
    private String descricao;
    private String marca;
    private Double precoCusto;
    private Double precoDeVenda;

    private String codigoDeBarras;
    private String embalagem;

    public Produto(String descricao, String marca, Double precoCusto, Double precoDeVenda, String codigoDeBarras, String embalagem) {
        this.descricao = descricao;
        this.marca = marca;
        this.precoCusto = precoCusto;
        this.precoDeVenda = precoDeVenda;
        this.codigoDeBarras = codigoDeBarras;
        this.embalagem = embalagem;
    }

    public Produto(String descricao, String marca, String codigoDeBarras, double precoDeVenda) {
        this.descricao = descricao;
        this.marca = marca;
        this.codigoDeBarras = codigoDeBarras;
        this.precoDeVenda = precoDeVenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoDeVenda() {
        return precoDeVenda;
    }

    public void setPrecoDeVenda(Double precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "descricao='" + descricao + '\'' +
                ", marca='" + marca + '\'' +
                ", codigoDeBarras='" + codigoDeBarras + '\'' +
                ", precoDeVenda='" + precoDeVenda + '\'' +
                '}';
    }

}
