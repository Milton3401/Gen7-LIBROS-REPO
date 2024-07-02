package com.jimenez.app.libreria.controllers;

import com.jimenez.app.libreria.models.DTO.LibroDTO;
import com.jimenez.app.libreria.models.Libro;
import com.jimenez.app.libreria.services.ILibrosService;
import com.jimenez.app.libreria.services.IService;
import com.jimenez.app.libreria.services.LibrosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/libros/listar")
public class ListarLibrosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos  la conexion que provee el filtro
        System.out.println("Entra al servlet");
        Connection conn= (Connection) req.getAttribute("conn");

        //Declaramos un objeto de tipo servicio
        LibrosService service = new LibrosService(conn);
        List<LibroDTO> librosDTO =service.listaLibroDTO();

        for (LibroDTO libro : librosDTO){
            System.out.println(libro.getNombreAutor());
        }
        req.setAttribute("libros", librosDTO);
        getServletContext().getRequestDispatcher("/listaLibros.jsp").forward(req, resp);

    }
}
