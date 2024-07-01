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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/autores/alta")
public class AltaAutorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/altaAutor.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =(Connection) req.getAttribute("conn");
        IService<Autor> service = new AutoresService(conn);
        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String descripcion = req.getParameter("descripcion");

        Map<String, String> errores = new HashMap<>();

        if(nombre==null || nombre.isBlank()){
            errores.put("nombre","el nombre es requerido!");
        }
        if(apPaterno==null || apPaterno.isBlank()){
            errores.put("apPaterno","el apellido paterno es requerido!");
        }
        if(apMaterno==null || apMaterno.isBlank()){
            errores.put("apMaterno","el apellido materno es requerido!");
        }
        if(descripcion==null || descripcion.isBlank()){
            errores.put("licencia","la licencia es requerida es requerido!");
        }
        if(errores.isEmpty()){
            Autor autor = new Autor();

            autor.setId(0L);
            autor.setNombre(nombre);
            autor.setApPaterno(apPaterno);
            autor.setApMaterno(apMaterno);
            autor.setDescripcion(descripcion);


            service.guardar(autor);
            resp.sendRedirect(req.getContextPath()+"/autores/listar");
        }else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaAutor.jsp")
                    .forward(req,resp);
        }
    }
}
