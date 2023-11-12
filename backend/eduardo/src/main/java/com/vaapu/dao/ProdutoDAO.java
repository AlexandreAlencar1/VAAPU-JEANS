package main.java.com.vaapu.dao;

import java.util.List;

import main.java.com.vaapu.model.Produto;

public interface ProdutoDAO {
    List<Produto> listarProdutos();
    Produto obterProdutoPorDescricao(String descricao);
    void adicionarProduto(Produto produto);
    void atualizarProduto(Produto produto);
    void excluirProduto(String descricao);
}
