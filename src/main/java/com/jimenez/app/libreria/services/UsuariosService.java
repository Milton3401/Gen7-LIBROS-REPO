package com.jimenez.app.libreria.services;

import com.jimenez.app.libreria.models.Autor;
import com.jimenez.app.libreria.models.Usuario;
import com.jimenez.app.libreria.repositories.AutoresRepository;
import com.jimenez.app.libreria.repositories.IRepository;
import com.jimenez.app.libreria.repositories.UsuariosRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuariosService implements IService<Usuario>{

    private IRepository<Usuario> usuariosRepo;

    public UsuariosService(Connection conn){
        usuariosRepo = new UsuariosRepository(conn);
    }
    @Override
    public List<Usuario> listar() {
        try{
            return usuariosRepo.listar();

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Usuario> getById(Long id) {
        try{
            return Optional.ofNullable(usuariosRepo.getById(id));

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try{
            usuariosRepo.guardar(usuario);

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            usuariosRepo.eliminar(id);

        }catch (SQLException e ){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }
}
