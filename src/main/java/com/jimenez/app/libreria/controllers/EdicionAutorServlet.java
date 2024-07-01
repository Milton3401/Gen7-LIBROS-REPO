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
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/autores/editar")
public class EdicionAutorServlet extends HttpServlet {

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
        if(descripcion==null || descripcion.isBlank()) {
            errores.put("licencia", "la descripcion del autor es requerida es requerido!");
        }
        long id;
        id = Long.parseLong(req.getParameter("id"));
        Autor autor = new Autor();
        autor.setId(id);
        autor.setNombre(nombre);
        autor.setApPaterno(apPaterno);
        autor.setApMaterno(apMaterno);
        autor.setDescripcion(descripcion);

        if (errores.isEmpty()) {
            service.guardar(autor);
            resp.sendRedirect(req.getContextPath() + "/autores/listar");
        }
        else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/edicionAutor.jsp")
                    .forward(req, resp);
        }
    }

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
                req.setAttribute("autor",autor);
                getServletContext().getRequestDispatcher("/edicionAutor.jsp")
                        .forward(req,resp);
            }
            else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el autor en la base de datos!");
            }
        }
        else{
            //resp.sendRedirect(req.getContextPath()+"/choferes/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}
