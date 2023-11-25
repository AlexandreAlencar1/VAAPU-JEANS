package com.backend.handlers;


import com.backend.controllers.ProdutoController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ProdutoHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "POST":
                ProdutoController.adicionarProduto(exchange);
                break;
            case "GET":
                ProdutoController.buscarProduto(exchange);
                break;
            case "PUT":
                ProdutoController.atualizarProduto(exchange);
                break;
            case "DELETE":
                ProdutoController.excluirProduto(exchange);
                break;
            default:
                ProdutoController.sendResponse(exchange, 405, "Method Not Allowed");
        }
    }
}
