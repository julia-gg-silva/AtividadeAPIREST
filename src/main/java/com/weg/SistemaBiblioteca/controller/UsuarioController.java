package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.weg.SistemaBiblioteca.exceptions.UsuarioNaoExisteException;
import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CriacaoUsuarioRespostaDTO> criarUsuario(
            @RequestBody CriacaoUsuarioRequisicaoDTO requisicaoUsuario) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarUsuario(requisicaoUsuario));

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoUsuarioRespostaDTO>> listarUsuarios() {
        List<CriacaoUsuarioRespostaDTO> respostaUsuario = new ArrayList<>();

        try {
            respostaUsuario = service.listarUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(respostaUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoUsuarioRespostaDTO> buscarUsuarioPorId(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarUsuarioPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoUsuarioRespostaDTO> atualizarUsuario(@PathVariable int id, @RequestBody CriacaoUsuarioRequisicaoDTO requisicaoUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarUsuario(id, requisicaoUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id){
        try{
            service.deletarUsuario(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}


