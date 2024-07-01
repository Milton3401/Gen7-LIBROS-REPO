package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.Libro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibrosRepository implements ILibrosRepository{

    private Connection conn; //Importamos de java.sql

    //constructor
    public LibrosRepository(Connection conn){
        this.conn=conn;
    }


    @Override
    public Long guardarReturnId(Libro libro) throws SQLException {
        return 0l;
    }

    @Override
    public List<Libro> listar() throws SQLException {
        return List.of();
    }

    @Override
    public Libro getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Libro libro) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
