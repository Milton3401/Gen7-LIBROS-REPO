package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Categoria;
import com.jimenez.app.libreria.services.AutoresService;
import com.jimenez.app.libreria.services.CategoriasService;
import com.jimenez.app.libreria.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/categorias/listar")
public class ListarCategoriasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos  la conexion que provee el filtro
        Connection conn= (Connection) req.getAttribute("conn");

        //Declaramos un objeto de tipo servicio
        IService<Categoria> service = new CategoriasService(conn);
        List<Categoria> categorias=service.listar();

        req.setAttribute("categorias", categorias);
        //getServletContext().getRequestDispatcher("/listaAutores.jsp").forward(req, resp);
    }
}
