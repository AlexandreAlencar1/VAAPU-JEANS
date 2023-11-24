package com.backend.controllers;

import com.backend.models.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutosController {
    private static final String URL = "jdbc:mysql://localhost:3306/vaapu_bd";
    private static final String USUARIO = "mysql";
    private static final String SENHA = "root";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void listarProdutos(HttpExchange exchange) throws IOException {
        try {
            // Adiciona cabeçalhos CORS
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

            try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
                String sql = "SELECT * FROM produtos";
                try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                    List<Produto> produtos = new ArrayList<>();
                    while (rs.next()) {
                        Produto produto = new Produto(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("tamanho"),
                                rs.getInt("quantidade"),
                                rs.getString("categoria"),
                                rs.getDouble("preco"),
                                rs.getString("imagem")
                        );
                        produtos.add(produto);
                    }

                    sendResponse(exchange, 200, "OK", objectMapper.writeValueAsString(produtos));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String statusMessage, String responseBody) throws IOException {
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
