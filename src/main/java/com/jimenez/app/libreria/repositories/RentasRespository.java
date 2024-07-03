package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.DTO.LibroDTO;
import com.jimenez.app.libreria.models.DTO.RentaDTO;
import com.jimenez.app.libreria.models.Libro;
import com.jimenez.app.libreria.models.Renta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentasRespository implements IRentasRespository{

    private Connection conn; //Importamos de java.sql

    //constructor
    public RentasRespository(Connection conn){
        this.conn=conn;
    }

    @Override
    public Long guardarReturnId(Renta renta) throws SQLException {
        return 0l;
    }

    @Override
    public List<RentaDTO> listarRentasDTO() throws SQLException {
        List<RentaDTO> rentaDTOS = new ArrayList<>();
        String sql="SELECT R.ID_RENTA, U.NOMBRE || ' ' || U.APELLIDO AS NOMBRE_COMPLETO_USUARIO, L.TITULO AS TITULO, " +
                "RES.NOMBRE || ' ' || RES.APELLIDO_PATERNO || ' ' || RES.APELLIDO_MATERNO AS NOMBRE_COMPLETO_RESPONSABLE, " +
                "R.FECHA_RENTA AS FECHA_RENTA, R.FECHA_DEVOLUCION AS FECHA_DEVOLUCION, R.ESTADO AS ESTADO, R.PRECIO AS PRECIO " +
                "FROM RENTAS R JOIN  USUARIOS U ON R.USUARIO_ID = U.ID_USUARIO JOIN LIBROS L ON R.LIBRO_ID = L.ID_LIBRO " +
                "JOIN  RESPONSABLES RES ON R.RESPONSABLE_ID = RES.ID_RESPONSABLE";

        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)){
            while (rs.next()){
                RentaDTO a = this.getRentaDTO(rs);
                rentaDTOS.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return rentaDTOS;
    }

    @Override
    public List<Renta> listar() throws SQLException {
        List<Renta> rentas = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT * FROM RENTAS")){
            while (rs.next()){
                Renta a = this.getRenta(rs);
                rentas.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return rentas;
    }

    @Override
    public Renta getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Renta renta) throws SQLException {
        String sql = "";

        if (renta.getId() != null && renta.getId() > 0) {
            sql = "update rentas set usuario_id=?, libro_id=?, " +
                    "fecha_renta=?, fecha_devolucion=?, estado=?," +
                    "precio=?, responsable_id where id_renta=?";
        } else {
            sql = "insert into rentas (id_renta,usuario_id," +
                    "libro_id,fecha_renta,fecha_devolucion,estado,precio," +
                    "responsable_id)" +
                    "values(SEQUENCE6.NEXTVAL,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (renta.getId() != null && renta.getId() > 0) {

                stmt.setLong(1, renta.getUsuariId());
                stmt.setLong(2, renta.getLibroId());
                stmt.setDate(3, Date.valueOf(renta.getFechaRenta()));
                stmt.setDate(4, Date.valueOf(renta.getFechaDevolucion()));
                stmt.setString(5, renta.getEstado());
                stmt.setLong(6, renta.getPrecio());
                stmt.setLong(7, renta.getResponsableId());
                stmt.setLong(8, renta.getId());
            } else {
                stmt.setLong(1, renta.getUsuariId());
                stmt.setLong(2, renta.getLibroId());
                stmt.setDate(3, Date.valueOf(renta.getFechaRenta()));
                stmt.setDate(4, Date.valueOf(renta.getFechaDevolucion()));
                stmt.setString(5, renta.getEstado());
                stmt.setLong(6, renta.getPrecio());
                stmt.setLong(7, renta.getResponsableId());

            }stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private Renta getRenta(ResultSet rs) throws SQLException{
        Renta a= new Renta();

        a.setId(rs.getLong("ID_RENTA"));
        a.setUsuariId(rs.getLong("USUARIO_ID"));
        a.setLibroId(rs.getLong("LIBRO_ID"));
        a.setFechaRenta(rs.getDate("FECHA_RENTA").toLocalDate());
        a.setFechaRenta(rs.getDate("DEVOLUCION").toLocalDate());
        a.setEstado(rs.getString("ESTADO"));
        a.setPrecio(rs.getLong("PRECIO"));
        a.setResponsableId(rs.getLong("RESPONSABLE_ID"));

        return a;
    }

    private RentaDTO getRentaDTO(ResultSet rs) throws SQLException{
        RentaDTO a= new RentaDTO();

        a.setRentaId(rs.getString("ID_RENTA"));
        a.setNombreUsuario(rs.getString("NOMBRE_COMPLETO_USUARIO"));
        a.setTitulo(rs.getString("TITULO"));
        a.setNombreResponsable(rs.getString("NOMBRE_COMPLETO_RESPONSABLE"));
        a.setFechaRenta(rs.getDate("FECHA_RENTA"));
        a.setFechaDevolucion(rs.getDate("FECHA_DEVOLUCION"));
        a.setEstado(rs.getString("ESTADO"));
        a.setPrecio(rs.getLong("PRECIO"));
        return a;
    }
}
