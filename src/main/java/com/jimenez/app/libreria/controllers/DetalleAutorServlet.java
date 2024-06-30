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
import java.util.Optional;

@WebServlet("/autores/detalle")
public class DetalleAutorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        IService<Autor> service = new AutoresService(conn);
        long id;

        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id=0l;
        }
        Autor autor= new Autor();

        if(id >0){
            Optional<Autor> o = service.getById(id);

            if(o.isPresent()){

                autor=o.get();
                System.out.println("yA SE ESTA MANDANDO EL AUTOR");
                req.setAttribute("autor",autor);
                getServletContext().getRequestDispatcher("/detalleAutor.jsp")
                        .forward(req,resp);
            }
            else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el autor en la base de datos!");
            }
        }
        else{
            resp.sendRedirect(req.getContextPath()+"/autores/listar");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}
