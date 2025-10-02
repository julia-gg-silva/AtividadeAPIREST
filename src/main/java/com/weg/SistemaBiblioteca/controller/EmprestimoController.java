package com.weg.SistemaBiblioteca.controller;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.weg.SistemaBiblioteca.dto.dataDevDTO.DataDevRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDTO;
import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.service.EmprestimoService;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CriacaoEmprestimoRespostaDTO> criarEmprestimo(@RequestBody CriacaoEmprestimoRequisicaoDTO requisicaoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarEmprestimo(requisicaoDTO));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoEmprestimoRespostaDTO>> listarEmprestimos() {
        List<CriacaoEmprestimoRespostaDTO> respostaEmprestimos = new ArrayList<>();
        try {
            respostaEmprestimos = service.listarEmprestimos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(respostaEmprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoEmprestimoRespostaDTO> buscarEmprestimoPorId(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarEmprestimoPorId(id));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoEmprestimoRespostaDTO> atualizarEmprestimo(@PathVariable int id, @RequestBody CriacaoEmprestimoRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarEmprestimo(id, requisicaoDTO));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<Void> atualizarDataDevolucao(@PathVariable int id, @RequestBody DataDevRequisicaoDTO respostaDTO){
        try{
            service.atualizarDataDevolucao(id, respostaDTO);
           return ResponseEntity.status(HttpStatus.NO_CONTENT)
                   .build();
        }catch (SQLException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable int id){
        try{
            service.deletarEmprestimo(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (SQLException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
