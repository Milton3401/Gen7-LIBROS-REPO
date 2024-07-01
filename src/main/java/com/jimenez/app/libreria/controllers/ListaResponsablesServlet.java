package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.models.Responsable;
import com.jimenez.app.libreria.services.IService;
import com.jimenez.app.libreria.services.ResponsablesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/responsables/listar")
public class ListaResponsablesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos  la conexion que provee el filtro
        Connection conn= (Connection) req.getAttribute("conn");

        //Declaramos un objeto de tipo servicio
        IService<Responsable> service = new ResponsablesService(conn);
        List<Responsable> responsables=service.listar();

        req.setAttribute("responsables", responsables);
        getServletContext().getRequestDispatcher("/listarResponsables.jsp").forward(req, resp);
    }
}
