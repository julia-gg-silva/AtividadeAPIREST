package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.repository.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO repository;

    public UsuarioService(UsuarioDAO repository) {
        this.repository = repository;
    }


    public Usuario criarUsuario(Usuario usuario) throws SQLException{
        return repository.salvar(usuario);
    }


    public List<Usuario> listarUsuarios() throws SQLException{
        return repository.buscarTodos();
    }

    public Usuario buscarUsuarioPorId(int id) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodos();

        for(Usuario u : usuarios){
            if(u.getId() == id){
                return repository.buscarPorId(id);
            }
        }
        throw new RuntimeException("Não existe nenhum Usuario com este ID!");
    }

    public Usuario atualizarUsuario(int id, Usuario usuario) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodos();
        usuario.setId(id);

        for(Usuario u: usuarios){
            if(u.getId() == usuario.getId()){
               return repository.atualizar(usuario);
            }
        }
        throw new RuntimeException("Não existe nenhum Usuario com este ID!");
    }

    public void deletarUsuario(int id) throws SQLException{
       repository.deletar(id);
    }
}
