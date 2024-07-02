package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.DTO.LibroDTO;
import com.jimenez.app.libreria.models.DTO.RentaDTO;
import com.jimenez.app.libreria.models.Libro;
import com.jimenez.app.libreria.models.Renta;

import java.sql.SQLException;
import java.util.List;

public interface IRentasRespository extends IRepository<Renta> {

    Long guardarReturnId(Renta renta) throws SQLException;

    List<RentaDTO> listarRentasDTO() throws SQLException;
}
