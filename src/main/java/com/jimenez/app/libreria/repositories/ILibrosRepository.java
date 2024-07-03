package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.DTO.LibroDTO;
import com.jimenez.app.libreria.models.Libro;

import java.sql.SQLException;
import java.util.List;

public interface ILibrosRepository extends IRepository<Libro> {

    Long guardarReturnId(Libro libro) throws SQLException;

    List<LibroDTO> listaLibroDTO() throws SQLException;

}
