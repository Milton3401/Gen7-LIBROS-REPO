package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Categoria;
import com.jimenez.app.libreria.models.DTO.LibroDTO;
import com.jimenez.app.libreria.models.DTO.RentaDTO;
import com.jimenez.app.libreria.models.Renta;
import com.jimenez.app.libreria.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RentasService implements IRentasService{

    private IRepository<Autor> autoresRepo;
    private IRepository<Categoria> categoriasRepo;
    private IRentasRespository rentasRepo;

    public RentasService(Connection connection){
        this.autoresRepo = new AutoresRepository(connection);
        this.categoriasRepo = new CategoriasRepository(connection);
        this.rentasRepo = new RentasRespository(connection);
    }
    @Override
    public List<Renta> listar() {

        return null;
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
}
