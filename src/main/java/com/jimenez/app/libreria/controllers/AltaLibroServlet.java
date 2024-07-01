package com.jimenez.app.libreria.controllers;


import com.jimenez.app.libreria.repositories.ILibrosRepository;
import com.jimenez.app.libreria.services.ILibrosService;
import com.jimenez.app.libreria.services.LibrosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/libros/alta")
public class AltaLibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn =(Connection) req.getAttribute("conn");
        ILibrosService service = new LibrosService(conn);

        req.setAttribute("autores",service.listarAutores());
        req.setAttribute("categorias",service.listarCategorias());
        getServletContext().getRequestDispatcher("/altaLibro.jsp")
                .forward(req,resp);
    }
}
