package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.repository.EmprestimoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoDAO repository;


    public EmprestimoService(EmprestimoDAO repository) {
        this.repository = repository;
    }

    public Emprestimo criarEmprestimo(Emprestimo emprestimo) throws SQLException {
        return repository.salvar(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() throws SQLException{
        return repository.buscarTodos();
    }

    public Emprestimo buscarEmprestimoPorId(int id) throws SQLException{
        List<Emprestimo> emprestimos = repository.buscarTodos();

        for(Emprestimo e: emprestimos){
            if(e.getId() == id){
                return repository.buscarPorId(id);
            }
        }
        throw new RuntimeException("Não existe algum Emprestimo com este ID!");
    }

    public Emprestimo atualizarEmprestimo(int id, Emprestimo emprestimo) throws SQLException{
        emprestimo.setId(id);
        List<Emprestimo> emprestimos = repository.buscarTodos();

        for(Emprestimo e : emprestimos){
            if(e.getId() == emprestimo.getId()){
                return repository.atualizar(emprestimo);
            }
        }
        throw new RuntimeException("Não existe algum Emprestimo com este ID!");
    }

    public Emprestimo atualizarDataDevolucao(int id, Emprestimo emprestimo) throws SQLException{
        emprestimo.setId(id);
        List<Emprestimo> emprestimos = repository.buscarTodos();

        for(Emprestimo e : emprestimos){
            if(e.getId() == emprestimo.getId()){
                return repository.atualizarDataDevolucao(emprestimo);
            }
        }
        throw new RuntimeException("Não existe algum Emprestimo com este ID!");
    }

    public void deletarEmprestimo(int id) throws SQLException{
        repository.deletar(id);
    }
}
