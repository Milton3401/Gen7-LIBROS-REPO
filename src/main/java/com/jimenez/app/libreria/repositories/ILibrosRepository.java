package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.Libro;

import java.sql.SQLException;

public interface ILibrosRepository extends IRepository<Libro> {

    Long guardarReturnId(Libro libro) throws SQLException;
}
