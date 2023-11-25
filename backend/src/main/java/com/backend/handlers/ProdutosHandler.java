package com.backend.handlers;

import com.backend.controllers.ProdutosController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ProdutosHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            ProdutosController.listarProdutos(exchange);
        } else {
            ProdutosController.sendResponse(exchange, 405, "Method Not Allowed");
        }
    }
}
