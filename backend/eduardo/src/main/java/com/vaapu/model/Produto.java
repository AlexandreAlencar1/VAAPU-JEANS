package main.java.com.vaapu.model;

public class Produto {
    private String descricao;
    private String tamanho;
    private int quantidade;
    private double preco;

    //estrutura construtor

    public Produto(String descricao, String tamanho, int quantidade, double preco) {
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    //  getters, setters.

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
