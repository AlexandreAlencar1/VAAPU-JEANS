package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Usuario;



public class UsuarioDAOImpl implements UsuarioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/vaapu_bd";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE_NAME = "usuarios";


public UsuarioDAOImpl() {
        // Configuração inicial, como a criação da tabela
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Criação da tabela de usuários
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "email VARCHAR(255) PRIMARY KEY," +
                    "nome VARCHAR(255)," +
                    "senha VARCHAR(255)," +
                    "dataNascimento DATE," +
                    "endereco VARCHAR(255)" +
                    ")";
            statement.executeUpdate(createTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                LocalDate dataNascimento = resultSet.getDate("dataNascimento").toLocalDate();
                String endereco = resultSet.getString("endereco");

                usuarios.add(new Usuario(nome, email, senha, dataNascimento, endereco));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public Usuario obterUsuarioPorEmail(String email) {
        Usuario usuario = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM " + TABLE_NAME + " WHERE email = ?")) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                LocalDate dataNascimento = resultSet.getDate("dataNascimento").toLocalDate();
                String endereco = resultSet.getString("endereco");

                usuario = new Usuario(nome, email, senha, dataNascimento, endereco);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO " + TABLE_NAME + " (email, nome, senha, dataNascimento, endereco) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getNome());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            preparedStatement.setString(5, usuario.getEndereco());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE " + TABLE_NAME + " SET nome = ?, senha = ?, dataNascimento = ?, endereco = ? WHERE email = ?")) {

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            preparedStatement.setString(4, usuario.getEndereco());
            preparedStatement.setString(5, usuario.getEmail());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum usuário foi atualizado. Verifique o e-mail do usuário.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluirUsuario(String email) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM " + TABLE_NAME + " WHERE email = ?")) {

            preparedStatement.setString(1, email);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum usuário foi excluído. Verifique o e-mail do usuário.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

