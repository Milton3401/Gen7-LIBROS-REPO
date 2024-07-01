package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Categoria;
import com.jimenez.app.libreria.models.Libro;
import com.jimenez.app.libreria.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LibrosService implements ILibrosService{

    //Atributos

    private IRepository <Autor> autoresRepo;
    private IRepository<Categoria> categoriasRepo;
    private  ILibrosRepository librosRepo;

    public LibrosService(Connection connection){
        this.autoresRepo = new AutoresRepository(connection);
        this.categoriasRepo = new CategoriasRepository(connection);

    }


    @Override
    public List<Autor> listarAutores() {
        try{
            return autoresRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try{
            return categoriasRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Long guardarReturnId(Libro libro) {
        try{
            return librosRepo.guardarReturnId(libro);

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Libro> listar() {
        return List.of();
    }

    @Override
    public Optional<Libro> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Libro libro) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
