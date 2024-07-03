package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.services.ILibrosService;
import com.jimenez.app.libreria.services.IRentasService;
import com.jimenez.app.libreria.services.LibrosService;
import com.jimenez.app.libreria.services.RentasService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/rentas/alta")
public class AltaRentaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =(Connection) req.getAttribute("conn");
        IRentasService service = new RentasService(conn);

        req.setAttribute("libros",service.listarLibros());
        req.setAttribute("responsables",service.listarReponsable());
        req.setAttribute("usuarios",service.listarUsuario());
        getServletContext().getRequestDispatcher("/altaRenta.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
