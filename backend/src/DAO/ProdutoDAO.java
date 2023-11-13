package DAO;

import java.util.List;

import entity.Produto;


public interface ProdutoDAO {
    List<Produto> listarProdutos();
    Produto obterProdutoPorDescricao(String descricao);
    void adicionarProduto(Produto produto);
    void atualizarProduto(Produto produto);
    void excluirProduto(String descricao);
}
