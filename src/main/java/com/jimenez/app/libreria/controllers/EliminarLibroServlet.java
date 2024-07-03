package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Libro;
import com.jimenez.app.libreria.services.IService;
import com.jimenez.app.libreria.services.LibrosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/libros/eliminar")
public class EliminarLibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        IService<Libro> service = new LibrosService(conn);
        long id;

        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id=0l;
        }
        Autor autor= new Autor();

        if(id >0){
            Optional<Libro> o = service.getById(id);
            if(o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/libros/listar");
            }
            else {
                System.out.println("El ide que se manda es: "+id);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el libro en la base de datos!");
            }
        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}
