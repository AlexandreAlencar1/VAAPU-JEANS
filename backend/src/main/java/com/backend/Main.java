package com.backend;

import com.backend.handlers.ProdutoHandler;
import com.backend.handlers.ProdutosHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/produto", new ProdutoHandler());
        server.createContext("/produtos", new ProdutosHandler());

        server.start();

        System.out.println("Servidor está rodando na porta 8080");
    }
}
