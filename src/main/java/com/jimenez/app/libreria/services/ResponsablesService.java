package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.Responsable;
import com.jimenez.app.libreria.repositories.IRepository;
import com.jimenez.app.libreria.repositories.ResponsablesRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ResponsablesService implements IService<Responsable> {

    //Atributos
    private IRepository<Responsable> responsablesRepo;

    //Constructor
    public ResponsablesService(Connection conn){
        responsablesRepo = new ResponsablesRepository(conn);
    }

    @Override
    public List<Responsable> listar() {
        try{
            return responsablesRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Responsable> getById(Long id) {
        try{
            return Optional.ofNullable(responsablesRepo.getById(id));//Revisamos si existe un chofer, en caso de noser asi pasa al catch

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Responsable responsable) {
        try{
            responsablesRepo.guardar(responsable);

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            responsablesRepo.eliminar(id);

        }catch (SQLException e ){

        }

    }
}
