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
import java.util.Optional;

@WebServlet("/responsables/eliminar")
public class EliminarResponsableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Responsable> service = new ResponsablesService(conn);
        long id;

        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id=0l;
        }
        Responsable responsable= new Responsable();

        if(id >0){
            Optional<Responsable> o = service.getById(id);
            if(o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/responsables/listar");
            }
            else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el trabajador en la base de datos!");
            }
        }
        else{
            // resp.sendRedirect(req.getContextPath()+"/choferes/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}
