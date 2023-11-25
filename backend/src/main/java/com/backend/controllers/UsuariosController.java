package com.backend.controllers;

import com.backend.models.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
//import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuariosController {
    // Defina suas variáveis de conexão com o banco de dados aqui
    private static final String URL = "jdbc:mysql://localhost:3306/vaapu_bd";
    private static final String USUARIO = "mysql";
    private static final String SENHA = "root";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void listarUsuarios(HttpExchange exchange) throws IOException {
        try {
            // Configuração CORS permitindo qualquer origem
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

            // Verifica se é uma requisição OPTIONS (pré-voo)
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

                exchange.sendResponseHeaders(200, -1); // Responde com sucesso para requisição OPTIONS
                return;
            }

            // Conexão com o banco de dados e listagem de usuários
            List<Usuario> usuarios = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
                String sql = "SELECT * FROM usuarios";
                try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        Usuario usuario = new Usuario(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("cpf"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("data_nascimento"),
                                rs.getString("endereco"));
                        usuarios.add(usuario);
                    }
                }
            }

            sendResponse(exchange, 200, "OK", objectMapper.writeValueAsString(usuarios));
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    // Outros métodos conforme necessário para manipulação de usuários

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
