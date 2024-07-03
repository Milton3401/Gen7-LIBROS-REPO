package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.DTO.LibroDTO;
import com.jimenez.app.libreria.models.Libro;
import com.jimenez.app.libreria.models.Usuario;
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
        Libro libro = null;

        try(PreparedStatement stms=
                    conn.prepareStatement("SELECT * FROM LIBROS WHERE ID_LIBRO=?")){
            stms.setLong(1,id);
            try(ResultSet rs = stms.executeQuery()){
                if(rs.next()){
                    libro=this.getLibro(rs);
                }else{
                }
            }
        }
        return libro;
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

    private LibroDTO getLibroDTO(ResultSet rs) throws SQLException{
        LibroDTO a= new LibroDTO();
        a.setId(rs.getLong("ID_LIBRO"));
        a.setTitulo(rs.getString("TITULO"));
        a.setNombreAutor(rs.getString("NOMBRE_AUTOR_COMPLETO"));
        a.setNombreCategoria(rs.getString("NOMBRE_CATEGORIA"));
        a.setAnio(rs.getLong("ANO_PUBLICACION"));
        a.setISBN(rs.getString("ISBN"));
        return a;
    }

    public List<LibroDTO> listaLibroDTO(){
        List<LibroDTO> libroDTOS = new ArrayList<>();
        String sql="SELECT L.ID_LIBRO AS ID_LIBRO, L.TITULO AS TITULO, A.NOMBRE || ' ' || A.APELLIDO_PATERNO || ' ' || A.APELLIDO_MATERNO AS NOMBRE_AUTOR_COMPLETO, " +
                "C.NOMBRE_CATEGORIA AS NOMBRE_CATEGORIA, L.ANO_PUBLICACION AS ANO_PUBLICACION, L.ISBN AS ISBN " +
                "FROM LIBROS L JOIN AUTORES A ON L.AUTOR_ID = A.ID_AUTOR JOIN CATEGORIAS C ON L.CATEGORIA_ID = C.ID_CATEGORIA";

        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)){
            while (rs.next()){
                LibroDTO a = this.getLibroDTO(rs);
                libroDTOS.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return libroDTOS;
    }

}
