package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.Autor;
import jakarta.servlet.SessionTrackingMode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoresRepository implements IRepository<Autor> {
    //Atributos
    private Connection conn; //Importamos de java.sql

    //constructor
    public AutoresRepository(Connection conn){
        this.conn=conn;
    }


    @Override
    public List<Autor> listar() throws SQLException {
        List<Autor> autores = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT * FROM AUTORES")){
            while (rs.next()){
                Autor a = this.getAutor(rs);
                autores.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return autores;
    }

    @Override
    public Autor getById(Long id) throws SQLException {
        Autor autor = null;

        try(PreparedStatement stms=
                    conn.prepareStatement("SELECT * FROM AUTORES WHERE ID_AUTOR=?")){
            stms.setLong(1,id);
            try(ResultSet rs = stms.executeQuery()){
                if(rs.next()){
                    autor=this.getAutor(rs);
                }else{
                    System.out.println("HCAE ALGO RARO Y ENTRA AL ELSE");
                }
            }
        }
        return autor;
    }

    @Override
    public void guardar(Autor autor) throws SQLException {
        String sql="";

        if(autor.getId()!= null && autor.getId()>0){
            sql="update autores set nombre=?, ap_paterno=?, " +
                    "ap_materno=?, descripcion=?" +
                    " where id_autor=?";
        }else{
            sql="insert into autores (ID_AUTOR,NOMBRE," +
                    "APELLIDO_PATERNO,APELLIDO_MATERNO,DESCRIPCION)" +
                    "values(SEQUENCE1.NEXTVAL,?,?,?,?)";
        }

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            if(autor.getId() != null && autor.getId() >0){
                stmt.setString(1,autor.getNombre());
                stmt.setString(2,autor.getApPaterno());
                stmt.setString(3,autor.getApMaterno());
                stmt.setString(4,autor.getDescripcion());
                stmt.setLong(5, autor.getId());
            }else{
                stmt.setString(1,autor.getNombre());
                stmt.setString(2,autor.getApPaterno());
                stmt.setString(3,autor.getApMaterno());
                stmt.setString(4,autor.getDescripcion());
            }stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        System.out.println("El id que llega es: "+id);
        String sql ="DELETE FROM AUTORES WHERE ID_AUTOR=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    //Mapear, transformar un renglon, fila, registro, row en un objeto de tipo chofer
    private Autor getAutor(ResultSet rs) throws SQLException{
        Autor a= new Autor();

        a.setId(rs.getLong("ID_AUTOR"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setApPaterno(rs.getString("APELLIDO_PATERNO"));
        a.setApMaterno(rs.getString("APELLIDO_MATERNO"));
        a.setDescripcion(rs.getString("DESCRIPCION"));
        return a;
    }
}
