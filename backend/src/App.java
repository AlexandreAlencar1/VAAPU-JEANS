import java.util.List;

import DAO.ProdutoDAO;
import DAO.ProdutoDAOImpl;
import entity.Produto;



public class App {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAOImpl();

        // Exemplo: Listar produtos
        List<Produto> produtos = produtoDAO.listarProdutos();
        for (Produto produto : produtos) {
            System.out.println(produto);
        }

        // Exemplo: Adicionar produto
        Produto novoProduto = new Produto("Novo Produto", "M", 10, 29.99);
        produtoDAO.adicionarProduto(novoProduto);

        // Exemplo: Atualizar produto
        Produto produtoExistente = produtoDAO.obterProdutoPorDescricao("Produto Existente");
        if (produtoExistente != null) {
            produtoExistente.setQuantidade(15);
            produtoDAO.atualizarProduto(produtoExistente);
        }

        // Exemplo: Excluir produto
        produtoDAO.excluirProduto("Produto a Excluir");
    }
}
