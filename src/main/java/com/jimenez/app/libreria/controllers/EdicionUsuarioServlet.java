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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/editar")
public class EdicionUsuarioServlet extends HttpServlet {
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
                usuario=o.get();
                req.setAttribute("usuario",usuario);
                getServletContext().getRequestDispatcher("/edicionUsuario.jsp")
                        .forward(req,resp);
            }
            else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el usuario en la base de datos!");
            }
        }
        else{
            //resp.sendRedirect(req.getContextPath()+"/choferes/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =(Connection) req.getAttribute("conn");
        IService<Usuario> service = new UsuariosService(conn);
        String nombre = req.getParameter("nombre");
        String apellidos = req.getParameter("apellido");
        String email = req.getParameter("email");
        String telefono = req.getParameter("descripcion");
        String direccion = req.getParameter("descripcion");
        Map<String, String> errores = new HashMap<>();

        if(nombre==null || nombre.isBlank()){
            errores.put("nombre","el nombre es requerido!");
        }
        if(apellidos==null || apellidos.isBlank()){
            errores.put("apellido","el apellido es requerido!");
        }
        if(email==null || email.isBlank()){
            errores.put("email","el email es requerido!");
        }
        if(telefono==null || telefono.isBlank()) {
            errores.put("telefono", "el telefono del usuario es requerido!");
        }
        if(direccion==null || direccion.isBlank()) {
            errores.put("direccion", "La direccion del usuario es requerido!");
        }

        long id;
        id = Long.parseLong(req.getParameter("id"));
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setApellido(apellidos);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);

        if (errores.isEmpty()) {
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios/listar");
        }
        else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/edicionUsuario.jsp")
                    .forward(req, resp);
        }
    }
}
