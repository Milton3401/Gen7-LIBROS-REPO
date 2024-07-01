package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Categoria;
import com.jimenez.app.libreria.models.Libro;

import java.util.List;

public interface ILibrosService extends IService<Libro> {

    List<Autor> listarAutores();

    List<Categoria> listarCategorias();

    Long guardarReturnId(Libro libro);

}
