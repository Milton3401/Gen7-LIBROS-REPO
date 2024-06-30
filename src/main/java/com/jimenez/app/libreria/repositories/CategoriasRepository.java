package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Categoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriasRepository implements IRepository<Categoria>{

    //Atributos
    private Connection conn; //Importamos de java.sql

    //constructor
    public CategoriasRepository(Connection conn){
        this.conn=conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT * FROM CATEGORIAS")){
            while (rs.next()){
                Categoria a = this.getCategoria(rs);
                categorias.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return categorias;
    }

    @Override
    public Categoria getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    //Mapear, transformar un renglon, fila, registro, row en un objeto de tipo chofer

    private Categoria getCategoria(ResultSet rs) throws SQLException{
        Categoria a= new Categoria();

        a.setId(rs.getLong("ID_CATEGORIA"));
        a.setCategoria(rs.getString("CATEGORIA"));

        return a;
    }
}
