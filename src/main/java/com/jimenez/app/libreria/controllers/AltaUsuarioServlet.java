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

@WebServlet("/usuarios/alta")
public class AltaUsuarioServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaUsuario.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =(Connection) req.getAttribute("conn");
        IService<Usuario> service = new UsuariosService(conn);
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String email = req.getParameter("email");
        String telefono = req.getParameter("telefono");
        String direccion = req.getParameter("direccion");

        Map<String, String> errores = new HashMap<>();

        if(nombre==null || nombre.isBlank()){
            errores.put("nombre","el nombre es requerido!");
        }
        if(apellido==null || apellido.isBlank()){
            errores.put("apellido","el apellido paterno es requerido!");
        }
        if(email==null || email.isBlank()){
            errores.put("email","el email materno es requerido!");
        }
        if(telefono==null || telefono.isBlank()){
            errores.put("telefono","el telefono es requerida es requerido!");
        }
        if(direccion==null || direccion.isBlank()){
            errores.put("descripcion","la descripcion es requerida es requerido!");
        }
        if(errores.isEmpty()){
            Usuario usuario = new Usuario();

            usuario.setId(0L);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setTelefono(telefono);
            usuario.setDireccion(direccion);


            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath()+"/usuarios/listar");
        }else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaUsuario.jsp")
                    .forward(req,resp);
        }
    }
}
