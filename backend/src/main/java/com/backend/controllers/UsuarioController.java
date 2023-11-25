package com.backend.controllers;

import com.backend.models.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

public class UsuarioController {
    // Defina suas variáveis de conexão com o banco de dados aqui
    private static final String URL = "jdbc:mysql://localhost:3306/vaapu_bd";
    private static final String USUARIO = "mysql";
    private static final String SENHA = "root";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void adicionarUsuario(HttpExchange exchange) throws IOException {
        try {
            // Configuração CORS permitindo qualquer origem
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

            // Verifica se é uma requisição OPTIONS (pré-voo)
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

                exchange.sendResponseHeaders(200, -1); // Responde com sucesso para requisição OPTIONS
                return;
            }

            // Recebe os dados do novo usuário do corpo da requisição
            InputStream requestBody = exchange.getRequestBody();
            Usuario novoUsuario = objectMapper.readValue(requestBody, Usuario.class);

            // Conexão com o banco de dados e inserção do novo usuário
            try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
                String sql = "INSERT INTO usuarios (nome, cpf, email, senha, data_nascimento, endereco) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, novoUsuario.getNome());
                    stmt.setString(2, novoUsuario.getCpf());
                    stmt.setString(3, novoUsuario.getEmail());
                    stmt.setString(4, novoUsuario.getSenha());
                    stmt.setString(5, novoUsuario.getDataNascimento());
                    stmt.setString(6, novoUsuario.getEndereco());
                    stmt.executeUpdate();

                    // Obtém o ID gerado para o novo usuário
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int novoId = generatedKeys.getInt(1);
                        novoUsuario.setId(novoId);
                    }
                }
            }

            sendResponse(exchange, 201, "Created", objectMapper.writeValueAsString(novoUsuario));
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    public static void buscarUsuario(HttpExchange exchange) throws IOException {
        // Implemente a lógica para buscar um usuário no banco de dados
    }

    public static void atualizarUsuario(HttpExchange exchange) throws IOException {
        // Implemente a lógica para atualizar um usuário no banco de dados
    }

    public static void excluirUsuario(HttpExchange exchange) throws IOException {
        // Implemente a lógica para excluir um usuário do banco de dados
    }

    // Outros métodos conforme necessário para manipulação de usuários

    public static void sendResponse(HttpExchange exchange, int statusCode, String statusMessage, String responseBody)
            throws IOException {
        exchange.sendResponseHeaders(statusCode, responseBody.length());
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(responseBody.getBytes());
        }
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String statusMessage) throws IOException {
        exchange.sendResponseHeaders(statusCode, 0);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(statusMessage.getBytes());
        }
    }
}
