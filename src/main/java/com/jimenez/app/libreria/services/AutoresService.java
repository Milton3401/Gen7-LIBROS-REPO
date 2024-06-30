package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.repositories.AutoresRepository;
import com.jimenez.app.libreria.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AutoresService implements IService<Autor>{

    private IRepository<Autor> autoresRepo;

    public AutoresService(Connection conn){
        autoresRepo = new AutoresRepository(conn);
    }

    @Override
    public List listar() {
        try{
            return autoresRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional getById(Long id) {
        try{
            return Optional.ofNullable(autoresRepo.getById(id));//Revisamos si existe un chofer, en caso de noser asi pasa al catch

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Autor autor) {
        try{
            autoresRepo.guardar(autor);

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {
        try{
            autoresRepo.eliminar(id);

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }
}
