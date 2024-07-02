package com.jimenez.app.libreria.repositories;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosRepository implements IRepository<Usuario> {

    //Atributos
    private Connection conn; //Importamos de java.sql

    public UsuariosRepository(Connection conn){
        this.conn=conn;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs=stmt.executeQuery("SELECT * FROM USUARIOS")){
            while (rs.next()){
                Usuario a = this.getUsuario(rs);
                usuarios.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public Usuario getById(Long id) throws SQLException {
        Usuario usuario = null;

        try(PreparedStatement stms=
                    conn.prepareStatement("SELECT * FROM USUARIOS WHERE ID_USUARIO=?")){
            stms.setLong(1,id);
            try(ResultSet rs = stms.executeQuery()){
                if(rs.next()){
                    usuario=this.getUsuario(rs);
                }else{
                }
            }
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql="";

        if(usuario.getId()!= null && usuario.getId()>0){
            sql="update usuarios set nombre=?, apellido=?, " +
                    "email=?, telefono=?, direccion=? " +
                    " where id_usuario=?";
        }else{
            sql="insert into usuarios (ID_USUARIO,NOMBRE," +
                    "APELLIDO,EMAIL,TELEFONO,DIRECCION)" +
                    "values(SEQUENCE3.NEXTVAL,?,?,?,?,?)";
        }

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (usuario.getId() != null && usuario.getId() > 0) {
                stmt.setString(1, usuario.getNombre());
                stmt.setString(2, usuario.getApellido());
                stmt.setString(3, usuario.getEmail());
                stmt.setString(4, usuario.getTelefono());
                stmt.setString(5, usuario.getDireccion());
                stmt.setLong(6, usuario.getId());
            } else {
                stmt.setString(1, usuario.getNombre());
                stmt.setString(2, usuario.getApellido());
                stmt.setString(3, usuario.getEmail());
                stmt.setString(4, usuario.getTelefono());
                stmt.setString(5, usuario.getDireccion());
            }
            stmt.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql ="DELETE FROM USUARIOS WHERE ID_USUARIO=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    //Mapear, transformar un renglon, fila, registro, row en un objeto de tipo chofer
    private Usuario getUsuario(ResultSet rs) throws SQLException{
        Usuario a= new Usuario();

        a.setId(rs.getLong("ID_USUARIO"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setApellido(rs.getString("APELLIDO"));
        a.setEmail(rs.getString("EMAIL"));
        a.setTelefono(rs.getString("TELEFONO"));
        a.setDireccion(rs.getString("DIRECCION"));
        return a;
    }
}
