package com.backend;

import com.backend.handlers.ProdutoHandler;
import com.backend.handlers.ProdutosHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/produto", new ProdutoHandler());
        server.createContext("/produtos", new ProdutosHandler());

        // Adiciona um manipulador global para tratar o CORS
        server.createContext("/", new GlobalHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Servidor está rodando na porta 8080");
    }

    // Manipulador global que aplica o filtro CORS
    static class GlobalHandler implements HttpHandler {
        private final CorsFilter corsFilter = new CorsFilter();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Aplica o filtro CORS
            corsFilter.doFilter(exchange, null);

            // Continua a cadeia de manipuladores (handlers)
            // Aqui você pode chamar outro manipulador se necessário
            // Exemplo: new YourOtherHandler().handle(exchange);

            // Adiciona uma resposta padrão para outras solicitações
            String response = "OK";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    // Filtro CORS
    static class CorsFilter {
        public void doFilter(HttpExchange exchange, Object chain) throws IOException {
            Headers headers = exchange.getResponseHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
            headers.add("Access-Control-Allow-Credentials", "true");
            headers.add("Access-Control-Max-Age", "3600");

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(200, -1);
                return;
            }
        }
    }
}
