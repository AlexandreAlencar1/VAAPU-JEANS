package com.backend.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Pode ser deixado em branco, dependendo das suas necessidades de inicialização
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

                System.out.println("Filter is running");

        // Cast para HttpServletRequest e HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Adiciona cabeçalhos CORS para permitir solicitações de qualquer origem
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Permite solicitações de OPTIONS (necessário para preflight)
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Continua a cadeia de filtros
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Pode ser deixado em branco, dependendo das suas necessidades de limpeza
    }
}
