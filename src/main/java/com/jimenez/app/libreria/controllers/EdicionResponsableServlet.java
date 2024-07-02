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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/responsables/editar")
public class EdicionResponsableServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Responsable> service = new ResponsablesService(conn);

        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String email = req.getParameter("email");
        String telefono = req.getParameter("telefono");
        String direccion = req.getParameter("direccion");
        String turno = req.getParameter("turno");



        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("matricula", "La matricula es obligatoria!");
        }
        if (apPaterno == null || apPaterno.isBlank()) {
            errores.put("tipoCamion", "Selecciona un tipo de camión!");
        }
        if (apMaterno == null || apMaterno.isBlank()) {
            errores.put("modelo", "Selecciona el modelo del camion!");
        }
        if (email == null || email.isBlank()) {
            errores.put("marca", "La marca es obligatoria!");
        }

        if (telefono == null || telefono.isBlank()) {
            errores.put("capacidad", "La capacidad es obligatoria!");
        }
        if (direccion == null || direccion.isBlank()) {
            errores.put("kilometraje", "El kilometraje es obligatorio!");
        }

        if (turno == null || turno.isBlank()) {
            errores.put("kilometraje", "El kilometraje es obligatorio!");
        }

        long id;
        id= Long.parseLong(req.getParameter("id"));

        Responsable responsable = new Responsable();
        responsable.setId(id);
        responsable.setNombre(nombre);
        responsable.setApPaterno(apPaterno);
        responsable.setApMaterno(apMaterno);
        responsable.setEmail(email);
        responsable.setTelefono(telefono);
        responsable.setDireccion(direccion);
        responsable.setTurno(turno);

        if(errores.isEmpty()){
            service.guardar(responsable);
            resp.sendRedirect(req.getContextPath()+"/responsables/listar");
        }else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/edicionResponsable.jsp")
                    .forward(req,resp);
        }
    }

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
        Responsable responsable = new Responsable();

        if(id >0){
            Optional<Responsable> o = service.getById(id);

            if(o.isPresent()){
                responsable=o.get();
                req.setAttribute("responsable",responsable);
                getServletContext().getRequestDispatcher("/edicionResponsable.jsp")
                        .forward(req,resp);
            }
            else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el trabajador en la base de datos!");
            }
        }
        else{
            //resp.sendRedirect(req.getContextPath()+"/choferes/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}
