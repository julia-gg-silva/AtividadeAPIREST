package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;


    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = new Usuario();

        try {
            newUsuario = service.criarUsuario(usuario);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUsuario;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            usuarios = service.listarUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable int id) {
        Usuario newUsuario = new Usuario();

        try {
            newUsuario = service.buscarUsuarioPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUsuario;
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        try {
            usuario = service.atualizarUsuario(id, usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable int id){
        try{
            service.deletarUsuario(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
