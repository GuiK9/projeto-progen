package com.example.supermercado.Models;

public class Produto {
    private String descricao;
    private String marca;
    private String codigoDeBarras;
    private double precoDeVenda;

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

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public double getPrecoDeVenda() {
        return precoDeVenda;
    }

    public void setPrecoDeVenda(double precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
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
