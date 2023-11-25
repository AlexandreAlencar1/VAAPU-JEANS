package com.backend.models;

public class Produto {
    private int id;
    private String nome;
    private String tamanho;
    private int quantidade;
    private String categoria;
    private double preco;
    private String imagem;

    public Produto() {}

    

    // getters e setters

    public Produto(int id, String nome, String tamanho, int quantidade, String categoria, double preco, String imagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.preco = preco;
        this.imagem = imagem;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    
}
