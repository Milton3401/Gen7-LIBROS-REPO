package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.models.Libro;
import com.jimenez.app.libreria.models.Renta;
import com.jimenez.app.libreria.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/rentas/alta")
public class AltaRentaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =(Connection) req.getAttribute("conn");
        IRentasService service = new RentasService(conn);

        req.setAttribute("libros",service.listarLibros());
        req.setAttribute("responsables",service.listarReponsable());
        req.setAttribute("usuarios",service.listarUsuario());
        getServletContext().getRequestDispatcher("/altaRenta.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =(Connection) req.getAttribute("conn");
        IService<Renta> service = new RentasService(conn);

        String tituloId = req.getParameter("titulo");
        String precio= req.getParameter("precio");
        String responsableId = req.getParameter("responsable");
        String fechaRenta = req.getParameter("fechaRenta");
        String usuarioId = req.getParameter("usuario");
        String disponibilidad = req.getParameter("disponible");
        String fechaEntrega = req.getParameter("fechaEntrega");
        LocalDate fechaEntregaLocalDate = LocalDate.parse(fechaEntrega);
        LocalDate fechaRentaLocalDate = LocalDate.parse(fechaRenta);

        Long tituloIdLong = Long.parseLong(tituloId);
        Long precioLong = Long.parseLong(precio);
        Long responsableIdLong = Long.parseLong(responsableId);
        Long usuarioIdLong = Long.parseLong(usuarioId);

        Map<String, String> errores = new HashMap<>();

        if(tituloId==null || tituloId.isBlank()){
            errores.put("titulo","el titulo es requerido!");
        }
        if(precio==null || precio.isBlank()){
            errores.put("precio","el precio de renta es requerido!");
        }
        if(responsableId==null || responsableId.isBlank()){
            errores.put("responsable","el trabajador en turno es requerido es requerido!");
        }
        if(fechaRenta==null || fechaRenta.isBlank()){
            errores.put("fechaRenta","la fecha de renta  es requerida es requerido!");
        }
        if(disponibilidad==null || disponibilidad.isBlank()){
            errores.put("disponibilidad","La disponibilidad es requerida!");
        }
        if(fechaEntrega==null || fechaEntrega.isBlank()){
            errores.put("fechaEntrega","la fecha de entrega es requerida!");
        }
        if(errores.isEmpty()){
            Renta renta = new Renta();

            renta.setId(0L);
            renta.setResponsableId(responsableIdLong);
            renta.setUsuariId(usuarioIdLong);
            renta.setPrecio(precioLong);
            renta.setEstado(disponibilidad);
            renta.setFechaRenta(fechaRentaLocalDate);
            renta.setFechaDevolucion(fechaEntregaLocalDate);
            renta.setLibroId(tituloIdLong);


            service.guardar(renta);
            resp.sendRedirect(req.getContextPath()+"/rentas/listar");
        }else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaRentas.jsp")
                    .forward(req,resp);
        }
    }
}
