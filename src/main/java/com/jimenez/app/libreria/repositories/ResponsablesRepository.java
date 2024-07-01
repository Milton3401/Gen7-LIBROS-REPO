package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.Responsable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponsablesRepository implements IRepository<Responsable>{
    //Atributos
    private Connection conn; //Importamos de java.sql

    //constructor
    public ResponsablesRepository(Connection conn){
        this.conn=conn;
    }


    @Override
    public List<Responsable> listar() throws SQLException {
        List<Responsable> responsables = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT * FROM RESPONSABLES")){
            while (rs.next()){
                Responsable a = this.getResponsable(rs);
                responsables.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return responsables;
    }

    @Override
    public Responsable getById(Long id) throws SQLException {
        Responsable responsable = null;

        try(PreparedStatement stms=
                    conn.prepareStatement("SELECT * FROM RESPONSABLES WHERE ID_RESPONSABLE=?")){
            stms.setLong(1,id);
            try(ResultSet rs = stms.executeQuery()){
                if(rs.next()){
                    responsable=this.getResponsable(rs);
                }else{
                    System.out.println("Entro al else checale aqui");
                }
            }
        }
        return responsable;
    }

    @Override
    public void guardar(Responsable responsable) throws SQLException {
        String sql="";

        if(responsable.getId()!= null && responsable.getId()>0){
            sql="update responsables set nombre=?, apellido_paterno=?, " +
                    "apellido_materno=?, email=?, telefono=?," +
                    "direccion=?, turno=?" +
                    " where id_responsable=?";
        }else{
            sql="insert into responsables (id_responsable,nombre," +
                    "apellido_paterno,apellido_materno,email,telefono,direccion,turno)" +
                    "values(SEQUENCE4.NEXTVAL,?,?,?,?,?,?,?)";
        }
        try(PreparedStatement stmt =conn.prepareStatement(sql)) {
            if (responsable.getId() != null && responsable.getId() > 0) {
                stmt.setString(1, responsable.getNombre());
                stmt.setString(2, responsable.getApPaterno());
                stmt.setString(3, responsable.getApMaterno());
                stmt.setString(4, responsable.getEmail());
                stmt.setString(5, responsable.getTelefono());
                stmt.setString(6, responsable.getDireccion());
                stmt.setString(7, responsable.getTurno());
                stmt.setLong(8, responsable.getId());

            } else {
                stmt.setString(1, responsable.getNombre());
                stmt.setString(2, responsable.getApPaterno());
                stmt.setString(3, responsable.getApMaterno());
                stmt.setString(4, responsable.getEmail());
                stmt.setString(5, responsable.getTelefono());
                stmt.setString(6, responsable.getDireccion());
                stmt.setString(7, responsable.getTurno());
            }stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql ="DELETE FROM RESPONSABLES WHERE ID_RESPONSABLE=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    private Responsable getResponsable(ResultSet rs) throws SQLException{
        Responsable a= new Responsable();

        a.setId(rs.getLong("ID_RESPONSABLE"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setApPaterno(rs.getString("APELLIDO_PATERNO"));
        a.setApMaterno(rs.getString("APELLIDO_MATERNO"));
        a.setEmail(rs.getString("EMAIL"));
        a.setTelefono(rs.getString("TELEFONO"));
        a.setDireccion(rs.getString("DIRECCION"));
        a.setTurno(rs.getString("TURNO"));

        return a;
    }
}
