package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.services.AutoresService;
import com.jimenez.app.libreria.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/autores/listar")
public class ListarAutoresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos  la conexion que provee el filtro
        Connection conn= (Connection) req.getAttribute("conn");

        //Declaramos un objeto de tipo servicio
        IService<Autor> service = new AutoresService(conn);
        List<Autor> autores=service.listar();

        /*for (Chofer c:choferes){
            resp.getWriter().println("<h1>"+c.getId()+" ->"
            +c.getNombre()+" ->"+c.getApPaterno()+"</h1>");
        }*/
        req.setAttribute("autores", autores);
        getServletContext().getRequestDispatcher("/listaAutores.jsp").forward(req, resp);
    }
}
