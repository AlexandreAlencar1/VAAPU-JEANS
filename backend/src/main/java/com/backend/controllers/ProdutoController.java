package com.backend.controllers;

import com.backend.models.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class ProdutoController {
    private static final String URL = "jdbc:mysql://localhost:3306/vaapu_bd";
    private static final String USUARIO = "mysql";
    private static final String SENHA = "root";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void adicionarProduto(HttpExchange exchange) throws IOException {
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
            
               

            InputStream requestBody = exchange.getRequestBody();
            Produto novoProduto = objectMapper.readValue(requestBody, Produto.class);

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
                String sql = "INSERT INTO produtos (nome, tamanho, quantidade, categoria, preco, imagem) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, novoProduto.getNome());
                    stmt.setString(2, novoProduto.getTamanho());
                    stmt.setInt(3, novoProduto.getQuantidade());
                    stmt.setString(4, novoProduto.getCategoria());
                    stmt.setDouble(5, novoProduto.getPreco());
                    stmt.setString(6, novoProduto.getImagem());
                    stmt.executeUpdate();

                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int novoId = generatedKeys.getInt(1);
                        novoProduto.setId(novoId);
                    }
                }
            }

            sendResponse(exchange, 201, "Created", objectMapper.writeValueAsString(novoProduto));
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    public static void buscarProduto(HttpExchange exchange) throws IOException {
        // Implemente o método buscarProduto conforme necessário
    }

    public static void atualizarProduto(HttpExchange exchange) throws IOException {
        try {
            // Configuração CORS permitindo qualquer origem
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "PUT, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

            // Verifica se é uma requisição OPTIONS (pré-voo)
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(200, -1); // Responde com sucesso para requisição OPTIONS
                return;
            }

            InputStream requestBody = exchange.getRequestBody();
            Produto produtoAtualizado = objectMapper.readValue(requestBody, Produto.class);

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
                String sql = "UPDATE produtos SET nome = ?, tamanho = ?, quantidade = ?, categoria = ?, preco = ?, imagem = ? WHERE id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(sql)) {
                    updateStmt.setString(1, produtoAtualizado.getNome());
                    updateStmt.setString(2, produtoAtualizado.getTamanho());
                    updateStmt.setInt(3, produtoAtualizado.getQuantidade());
                    updateStmt.setString(4, produtoAtualizado.getCategoria());
                    updateStmt.setDouble(5, produtoAtualizado.getPreco());
                    updateStmt.setString(6, produtoAtualizado.getImagem());
                    updateStmt.setInt(7, produtoAtualizado.getId());
                    updateStmt.executeUpdate();

                    sendResponse(exchange, 200, "OK", objectMapper.writeValueAsString(produtoAtualizado));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    public static void excluirProduto(HttpExchange exchange) throws IOException {
        try {
            // Configuração CORS permitindo qualquer origem
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

            // Verifica se é uma requisição OPTIONS (pré-voo)
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(200, -1); // Responde com sucesso para requisição OPTIONS
                return;
            }

            String path = exchange.getRequestURI().getPath();
            String[] pathSegments = path.split("/");
            if (pathSegments.length == 3) {
                int produtoId = Integer.parseInt(pathSegments[2]);

                try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
                    String sql = "DELETE FROM produtos WHERE id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, produtoId);
                        int rowCount = stmt.executeUpdate();

                        if (rowCount > 0) {
                            sendResponse(exchange, 200, "OK");
                        } else {
                            sendResponse(exchange, 404, "Not Found");
                        }
                    }
                }
            } else {
                sendResponse(exchange, 400, "Bad Request");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    // Outros métodos conforme necessário

    public static void sendResponse(HttpExchange exchange, int statusCode, String statusMessage) throws IOException {
        exchange.sendResponseHeaders(statusCode, 0);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(statusMessage.getBytes());
        }
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String statusMessage, String responseBody) throws IOException {
        exchange.sendResponseHeaders(statusCode, responseBody.length());
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(responseBody.getBytes());
        }
    }
}
