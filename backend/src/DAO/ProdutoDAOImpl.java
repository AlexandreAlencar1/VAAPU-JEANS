package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Produto;


public class ProdutoDAOImpl implements ProdutoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/vaapu_bd";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE_NAME = "produto";

    public ProdutoDAOImpl() {
        // Configuração inicial, como a criação da tabela
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Criação da tabela de produtos
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "descricao VARCHAR(255) PRIMARY KEY," +
                    "tamanho VARCHAR(255)," +
                    "quantidade INT," +   //escolhas
                    "preco DOUBLE" +
                    ")";
            statement.executeUpdate(createTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Listar produtos
    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         Statement statement = connection.createStatement()) {

        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

        while (resultSet.next()) {
            String descricao = resultSet.getString("descricao");
            String tamanho = resultSet.getString("tamanho");
            int quantidade = resultSet.getInt("quantidade");
            double preco = resultSet.getDouble("preco");

            produtos.add(new Produto(descricao, tamanho, quantidade, preco));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return produtos;
}

    // Obter produto
    @Override
    public Produto obterProdutoPorDescricao(String descricao) {

        Produto produto = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT * FROM " + TABLE_NAME + " WHERE descricao = ?")) {

        preparedStatement.setString(1, descricao);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String tamanho = resultSet.getString("tamanho");
            int quantidade = resultSet.getInt("quantidade");
            double preco = resultSet.getDouble("preco");

            produto = new Produto(descricao, tamanho, quantidade, preco);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return produto;
}

   // add produto
    @Override
    public void adicionarProduto(Produto produto) {
         try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO " + TABLE_NAME + " (descricao, tamanho, quantidade, preco) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setString(2, produto.getTamanho());
            preparedStatement.setInt(3, produto.getQuantidade());
            preparedStatement.setDouble(4, produto.getPreco());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
           // fazer tratamento
        }
        
    }

    // Update produto
    @Override
    public void atualizarProduto(Produto produto) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE " + TABLE_NAME + " SET tamanho = ?, quantidade = ?, preco = ? WHERE descricao = ?")) {

            preparedStatement.setString(1, produto.getTamanho());
            preparedStatement.setInt(2, produto.getQuantidade());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setString(4, produto.getDescricao());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum produto foi atualizado. Verifique a descrição do produto.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //fazer tratamento
        }
    }

    // delete produto 
    @Override
    public void excluirProduto(String descricao) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM " + TABLE_NAME + " WHERE descricao = ?")) {

       preparedStatement.setString(1, descricao);

       int linhasAfetadas = preparedStatement.executeUpdate();

       if (linhasAfetadas == 0) {
           throw new SQLException("Nenhum produto foi excluído. Verifique a descrição do produto.");
       }

   } catch (SQLException e) {
       e.printStackTrace();
       // //fazer tratamento
   }
}

    // Métodos auxiliares
}
