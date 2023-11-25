package com.backend.controllers;

import com.backend.models.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthController {
       // Defina suas variáveis de conexão com o banco de dados aqui
       private static final String URL = "jdbc:mysql://localhost:3306/vaapu_bd";
       private static final String USUARIO = "mysql";
       private static final String SENHA = "root";
       private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void autenticarUsuario(HttpExchange exchange) throws IOException {
        try {
            // Configuração CORS permitindo qualquer origem
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

            // Verifica se é uma requisição OPTIONS (pré-voo)
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(200, -1); // Responde com sucesso para requisição OPTIONS
                return;
            }

            // Recebe os dados de login do corpo da requisição
            InputStream requestBody = exchange.getRequestBody();
            Usuario usuarioLogin = objectMapper.readValue(requestBody, Usuario.class);

            // Conexão com o banco de dados para verificar as credenciais do usuário
            try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA)) {
                String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, usuarioLogin.getEmail());
                    stmt.setString(2, usuarioLogin.getSenha());
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            // Usuário autenticado, gera um token de autenticação (aqui você pode usar JWT,
                            // por exemplo)
                            String authToken = generateAuthToken(usuarioLogin.getEmail());
                            sendResponse(exchange, 200, "OK", authToken);
                        } else {
                            sendResponse(exchange, 401, "Unauthorized");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "Internal Server Error");
        }
    }

    private static final String SECRET_KEY = "123ACB"; // Substitua pela sua chave secreta

    private static String generateAuthToken(String email) {
        // Geração do token JWT
        return Jwts.builder()
                .setSubject(email) // Assunto do token (no caso, o email do usuário)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // Assinatura usando o algoritmo HS256 e a
                                                                           // chave secreta
                .compact(); // Compactação do token em uma string
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String statusMessage) throws IOException {
        // Método para enviar uma resposta HTTP com o código de status e mensagem
        // fornecidos
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String statusMessage, String responseBody)
            throws IOException {
        // Método para enviar uma resposta HTTP com o código de status, mensagem e corpo
        // de resposta fornecidos
    }
}
