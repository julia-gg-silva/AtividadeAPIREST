package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.model.Livro;

import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.service.LivroService;
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
    public Livro criarLivro(@RequestBody Livro livro){
        Livro newLivro = new Livro();

        try{
            newLivro = service.salvarLivro(livro);
            return newLivro;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newLivro;
    }

    @GetMapping
    public List<Livro> listarLivros(){
        List<Livro> livros = new ArrayList<>();
        try {
            livros = service.listarLivros();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return livros;
    }

    @GetMapping("/{id}")
    public Livro buscarLivroPorId(@PathVariable int id){
        Livro livro = new Livro();

        try{
            livro = service.buscarLivroPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return livro;
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable int id, @RequestBody Livro livro){
        try{
          livro = service.atualizarLivro(id, livro);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  livro;
    }

    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable int id){
        try{
            service.deletarLivro(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
