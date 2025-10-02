package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.dto.livro.CriacaoLivroRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.livro.CriacaoLivroRespostaDTO;
import com.weg.SistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.weg.SistemaBiblioteca.model.Livro;

import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoLivroRespostaDTO> criarLivro(@RequestBody CriacaoLivroRequisicaoDTO requisicaoLivro){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.salvarLivro(requisicaoLivro));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoLivroRespostaDTO>> listarLivros(){
        List<CriacaoLivroRespostaDTO> respostaLivros = new ArrayList<>();
        try {
            respostaLivros = service.listarLivros();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(respostaLivros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoLivroRespostaDTO> buscarLivroPorId(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarLivroPorId(id));
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoLivroRespostaDTO> atualizarLivro(@PathVariable int id, @RequestBody CriacaoLivroRequisicaoDTO requisicaoLivro){
        try{
          return ResponseEntity.status(HttpStatus.OK)
                  .body(service.atualizarLivro(id, requisicaoLivro));

        }catch (SQLException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable int id){
        try{
            service.deletarLivro(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                            .build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
