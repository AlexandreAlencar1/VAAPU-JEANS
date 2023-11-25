package com.backend.handlers;

public class UsuarioHandler {
    
}


// package com.backend.handlers;

// import com.backend.controllers.UsuarioController;
// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;

// import java.io.IOException;

// public class UsuarioHandler implements HttpHandler {
//     @Override
//     public void handle(HttpExchange exchange) throws IOException {
//         String method = exchange.getRequestMethod();
        
//         switch (method) {
//             case "POST":
//                 UsuarioController.adicionarUsuario(exchange);
//                 break;
//             case "GET":
//                 UsuarioController.buscarUsuario(exchange);
//                 break;
//             case "PUT":
//                 UsuarioController.atualizarUsuario(exchange);
//                 break;
//             case "DELETE":
//                 UsuarioController.excluirUsuario(exchange);
//                 break;
//             default:
//                 UsuarioController.sendResponse(exchange, 405, "Method Not Allowed");
//         }
//     }
// }
