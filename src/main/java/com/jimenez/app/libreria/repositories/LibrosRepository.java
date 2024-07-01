package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.Libro;
import jakarta.servlet.SessionTrackingMode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibrosRepository implements ILibrosRepository{

    private Connection conn; //Importamos de java.sql

    //constructor
    public LibrosRepository(Connection conn){
        this.conn=conn;
    }


    @Override
    public Long guardarReturnId(Libro libro) throws SQLException {
        return 0l;
    }

    @Override
    public List<Libro> listar() throws SQLException {
        List<Libro> libros = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT * FROM LIBROS")){
            while (rs.next()){
                Libro a = this.getLibro(rs);
                libros.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return libros;
    }

    @Override
    public Libro getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Libro libro) throws SQLException {
        String sql="";

        if(libro.getId()!= null && libro.getId()>0){
            sql="update libros set titulo=?, autor_id=?, " +
                    "categoria_id=?, ano_publicacion=?, isbn=?," +
                    "stock=? where id_libro=?";
        }else{
            sql="insert into libros (id_libro,titulo," +
                    "autor_id,categoria_id,ano_publicacion,isbn,stock)" +
                    "values(SEQUENCE5.NEXTVAL,?,?,?,?,?,?)";
        }
        try(PreparedStatement stmt =conn.prepareStatement(sql)){
            if(libro.getId() != null && libro.getId() > 0){

                stmt.setString(1,libro.getTitulo());
                stmt.setLong(2,libro.getAutorId());
                stmt.setLong(3,libro.getCategoriaId());
                stmt.setLong(4,libro.getAnoPublicacion());
                stmt.setString(5,libro.getIsbn());
                stmt.setLong(6,libro.getStock());
                stmt.setLong(7,libro.getId());
            }else{
                System.out.println("Entro a la insercion del libro");
                stmt.setString(1,libro.getTitulo());
                stmt.setLong(2,libro.getAutorId());
                stmt.setLong(3,libro.getCategoriaId());
                stmt.setLong(4,libro.getAnoPublicacion());
                stmt.setString(5,libro.getIsbn());
                stmt.setLong(6,libro.getStock());
            }stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
    private Libro getLibro(ResultSet rs) throws SQLException{
        Libro a= new Libro();

        a.setId(rs.getLong("ID_LIBRO"));
        a.setTitulo(rs.getString("TITULO"));
        a.setAutorId(rs.getLong("AUTOR_ID"));
        a.setCategoriaId(rs.getLong("CATEGORIA_ID"));
        a.setAnoPublicacion(rs.getLong("ANO_PUBLICACION"));
        a.setIsbn(rs.getString("ISBN"));
        a.setStock(rs.getLong("STOCK"));
        return a;
    }

}
