package com.jimenez.app.libreria.controllers;


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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/libros/alta")
public class AltaLibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn =(Connection) req.getAttribute("conn");
        ILibrosService service = new LibrosService(conn);

        req.setAttribute("autores",service.listarAutores());
        req.setAttribute("categorias",service.listarCategorias());
        getServletContext().getRequestDispatcher("/altaLibro.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn =(Connection) req.getAttribute("conn");
        IService<Libro> service = new LibrosService(conn);

        String titulo = req.getParameter("titulo");
        String autorId= req.getParameter("autorId");
        String categoriaId = req.getParameter("categoriaId");
        String anoPublicacion = req.getParameter("anoPublicacion");
        String isbn = req.getParameter("isbn");
        String stock = req.getParameter("stock");

        Long autorIdLong = Long.parseLong(autorId);
        Long categoriaIdLong = Long.parseLong(categoriaId);
        Long anoPublicacionLong = Long.parseLong(anoPublicacion);
        Long stockLong = Long.parseLong(stock);

        System.out.println(titulo);
        System.out.println(autorIdLong);
        System.out.println(categoriaIdLong);
        System.out.println(anoPublicacionLong);
        System.out.println(isbn);
        System.out.println(stockLong);


        Map<String, String> errores = new HashMap<>();

        if(titulo==null || titulo.isBlank()){
            errores.put("nombre","el nombre es requerido!");
        }
        if(autorId==null || autorId.isBlank()){
            errores.put("apPaterno","el apellido paterno es requerido!");
        }
        if(categoriaId==null || categoriaId.isBlank()){
            errores.put("apMaterno","el apellido materno es requerido!");
        }
        if(anoPublicacion==null || anoPublicacion.isBlank()){
            errores.put("licencia","la licencia es requerida es requerido!");
        }
        if(isbn==null || isbn.isBlank()){
            errores.put("telefono","el telefono es requerido!");
        }
        if(stock==null || stock.isBlank()){
            errores.put("fechaNacimiento","la fecha de nacimiento es requerida!");
        }
        if(errores.isEmpty()){
            Libro libro = new Libro();

            libro.setId(0L);
            libro.setTitulo(titulo);
            libro.setAutorId(autorIdLong);
            libro.setCategoriaId(categoriaIdLong);
            libro.setAnoPublicacion(anoPublicacionLong);
            libro.setIsbn(isbn);
            libro.setStock(stockLong);

            service.guardar(libro);
            resp.sendRedirect(req.getContextPath()+"/libros/listar");
        }else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaLibro.jsp")
                    .forward(req,resp);
        }
    }
}
