package loja;

import java.util.ArrayList;
import java.util.List;
import entity.Produto;

public class CarrinhoCompra {
    //Adicionar ao carrinho: Crie uma classe chamada CarrinhoProduto para representar o carrinho
    //Adicione uma lista de produto como produto.
    //Implemente um construtor para inicializar a lista de itens e metodos para adicionar um item(adicionarProduto),
    //obter a lista de itens(getProdutos),calcular o total do carrinho(calcularTotal), e gerar uma representação em String do carrinho(toString).

    
    private List<Produto> produtos;

    public  CarrinhoCompra() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double calcularTotal() {
        double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Carrinho de Produtos:\n");
        for (Produto produto : produtos) {
            sb.append(produto).append("\n");
        }
        sb.append("Total: R$ ").append(calcularTotal());
        return sb.toString();
    }
}


