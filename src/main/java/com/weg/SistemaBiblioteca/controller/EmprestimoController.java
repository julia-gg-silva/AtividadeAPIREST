package com.weg.SistemaBiblioteca.controller;

import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;


    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public Emprestimo criarEmprestimo(@RequestBody Emprestimo emprestimo) {
        Emprestimo newEmprestimo = new Emprestimo();
        try {
            newEmprestimo = service.criarEmprestimo(emprestimo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newEmprestimo;
    }

    @GetMapping
    public List<Emprestimo> listarEmprestimos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try {
            emprestimos = service.listarEmprestimos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    @GetMapping("/{id}")
    public Emprestimo buscarEmprestimoPorId(@PathVariable int id) {
        Emprestimo emprestimo = new Emprestimo();
        try {
            emprestimo = service.buscarEmprestimoPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimo;
    }

    @PutMapping("/{id}")
    public Emprestimo atualizarEmprestimo(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.atualizarEmprestimo(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  newEmprestimo;
    }

    @PutMapping("/{id}/devolucao")
    public Emprestimo atualizarDataDevolucao(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.atualizarDataDevolucao(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newEmprestimo;
    }

    @DeleteMapping("/{id}")
    public void deletarEmprestimo(@PathVariable int id){
        try{
            service.deletarEmprestimo(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
