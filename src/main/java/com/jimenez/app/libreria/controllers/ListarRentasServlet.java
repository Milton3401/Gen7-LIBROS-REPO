package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.models.DTO.LibroDTO;
import com.jimenez.app.libreria.models.DTO.RentaDTO;
import com.jimenez.app.libreria.services.LibrosService;
import com.jimenez.app.libreria.services.RentasService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/rentas/listar")
public class ListarRentasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos  la conexion que provee el filtro
        System.out.println("Entra al servlet");
        Connection conn= (Connection) req.getAttribute("conn");

        //Declaramos un objeto de tipo servicio
        RentasService service = new RentasService(conn);
        List<RentaDTO> rentasDTO =service.listaRentaDTO();

        for (RentaDTO renta : rentasDTO){
            System.out.println(renta.getFechaRenta());
        }
        req.setAttribute("rentasDTO", rentasDTO);
        getServletContext().getRequestDispatcher("/listaRentas.jsp").forward(req, resp);
    }
}
