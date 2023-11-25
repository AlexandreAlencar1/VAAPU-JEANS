package com.backend.handlers;

public class UsuariosHandler {
    
}


// package com.backend.handlers;

// import com.backend.controllers.UsuariosController;
// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;

// import java.io.IOException;

// public class UsuariosHandler implements HttpHandler {
//     @Override
//     public void handle(HttpExchange exchange) throws IOException {
//         if ("GET".equals(exchange.getRequestMethod())) {
//             UsuariosController.listarUsuarios(exchange);
//         } else {
//             UsuariosController.sendResponse(exchange, 405, "Method Not Allowed");
//         }
//     }
// }
