package com.jimenez.app.libreria.controllers;


import com.jimenez.app.libreria.models.Responsable;
import com.jimenez.app.libreria.services.IService;
import com.jimenez.app.libreria.services.ResponsablesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/responsables/alta")
public class AltaResponsableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaResponsable.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =(Connection) req.getAttribute("conn");
        IService<Responsable> service = new ResponsablesService(conn);
        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String email = req.getParameter("email");
        String telefono = req.getParameter("telefono");
        String direccion = req.getParameter("direccion");
        String turno = req.getParameter("turno");

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
        if(email==null || email.isBlank()){
            errores.put("licencia","la licencia es requerida es requerido!");
        }
        if(telefono==null || telefono.isBlank()){
            errores.put("telefono","el telefono es requerido!");
        }
        if(direccion==null || direccion.isBlank()){
            errores.put("fechaNacimiento","la fecha de nacimiento es requerida!");
        }

        if(turno==null || turno.isBlank()){
            errores.put("fechaNacimiento","la fecha de nacimiento es requerida!");
        }
        if(errores.isEmpty()){
            Responsable responsable = new Responsable();

            responsable.setId(0L);
            responsable.setNombre(nombre);
            responsable.setApPaterno(apPaterno);
            responsable.setApMaterno(apMaterno);
            responsable.setEmail(email);
            responsable.setTelefono(telefono);
            responsable.setDireccion(direccion);
            responsable.setTurno(turno);

            service.guardar(responsable);
            resp.sendRedirect(req.getContextPath()+"/responsables/listar");
        }else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaResponsable.jsp")
                    .forward(req,resp);
        }
    }
}
