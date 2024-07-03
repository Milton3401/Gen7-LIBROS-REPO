package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.*;
import com.jimenez.app.libreria.models.DTO.RentaDTO;
import com.jimenez.app.libreria.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RentasService implements IRentasService{

    private IRepository<Libro> librosRepo;
    private IRepository<Responsable> responsableRepo;
    private IRepository<Usuario> usuariosRepo;
    private IRentasRespository rentasRepo;

    public RentasService(Connection connection){
        this.librosRepo = new LibrosRepository(connection);
        this.responsableRepo = new ResponsablesRepository(connection);
        this.usuariosRepo= new UsuariosRepository(connection);
        this.rentasRepo = new RentasRespository(connection);
    }
    @Override
    public List<Renta> listar() {
        try{
            return rentasRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Renta> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Renta renta) {
        try{
            rentasRepo.guardar(renta);

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {

    }

    public List<RentaDTO> listaRentaDTO(){
        try{
            return rentasRepo.listarRentasDTO();
        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Libro> listarLibros() {
        try{
            return librosRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Responsable> listarReponsable() {
        try{
            return responsableRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Usuario> listarUsuario() {
        try{
            return usuariosRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Long guardarReturnId(Libro libro) {
        return 0l;
    }
}
