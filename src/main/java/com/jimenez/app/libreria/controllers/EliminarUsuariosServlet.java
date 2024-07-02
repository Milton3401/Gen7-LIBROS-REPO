package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Usuario;
import com.jimenez.app.libreria.services.AutoresService;
import com.jimenez.app.libreria.services.IService;
import com.jimenez.app.libreria.services.UsuariosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/usuarios/eliminar")
public class EliminarUsuariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Usuario> service = new UsuariosService(conn);
        long id;

        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id=0l;
        }
        Usuario usuario= new Usuario();

        if(id >0){
            Optional<Usuario> o = service.getById(id);
            if(o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/usuarios/listar");
            }
            else {
                System.out.println("El ide que se manda es: "+id);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el usuario en la base de datos!");
            }
        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}
