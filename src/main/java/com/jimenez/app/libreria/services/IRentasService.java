package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.*;

import java.util.List;

public interface IRentasService extends IService<Renta>{

    List<Libro> listarLibros();

    List<Responsable> listarReponsable();

    List<Usuario> listarUsuario();

    Long guardarReturnId(Libro libro);
}
