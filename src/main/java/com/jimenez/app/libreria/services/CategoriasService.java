package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Categoria;
import com.jimenez.app.libreria.repositories.AutoresRepository;
import com.jimenez.app.libreria.repositories.CategoriasRepository;
import com.jimenez.app.libreria.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriasService implements IService<Categoria>{

    private IRepository<Categoria> categoriaRepo;

    public CategoriasService(Connection conn){
        categoriaRepo = new CategoriasRepository(conn);
    }

    @Override
    public List<Categoria> listar() {
        try{
            return categoriaRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Categoria> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Categoria categoria) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
