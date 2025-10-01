package com.weg.SistemaBiblioteca.controller;

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
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro livro){
        Livro newLivro = new Livro();

        try{
            newLivro = service.salvarLivro(livro);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newLivro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros(){
        List<Livro> livros = new ArrayList<>();
        try {
            livros = service.listarLivros();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable int id){
        Livro livro = new Livro();

        try{
            livro = service.buscarLivroPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable int id, @RequestBody Livro livro){
        try{
          livro = service.atualizarLivro(id, livro);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable int id){
        try{
            service.deletarLivro(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
