package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.model.Livro;
import com.weg.SistemaBiblioteca.repository.LivroDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroDAO repository;

    public LivroService(LivroDAO repository) {
        this.repository = repository;
    }

    public Livro salvarLivro(Livro livro) throws SQLException {
        return repository.salvar(livro);
    }

    public List<Livro> listarLivros() throws SQLException{
        return repository.buscarTodos();
    }

    public Livro buscarLivroPorId(int id) throws SQLException{
        List<Livro> livros = repository.buscarTodos();

        for(Livro l : livros){
            if(l.getId() == id){
                return repository.buscarPorId(id);
            }
        }
        throw new RuntimeException("Não existe nenhum livro com este ID!");
    }

    public Livro atualizarLivro(int id, Livro livro) throws SQLException{
        List<Livro> livros = repository.buscarTodos();
        livro.setId(id);

        for(Livro l: livros){
            if(l.getId() == livro.getId()){
               return repository.atualizar(livro);
            }
        }
        throw new RuntimeException("Não existe nenhum livro com este ID");
    }

    public void deletarLivro(int id) throws SQLException{
        List<Livro> livros = repository.buscarTodos();

        for(Livro l : livros){
            if(l.getId() == id){
                repository.deletar(id);
                return;
            }
        }
        throw new RuntimeException("Não existe nenhum livro com este ID");
    }
}
