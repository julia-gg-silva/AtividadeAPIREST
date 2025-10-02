package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.dto.livro.CriacaoLivroRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.livro.CriacaoLivroRespostaDTO;
import com.weg.SistemaBiblioteca.exceptions.LivroNaoExisteException;
import com.weg.SistemaBiblioteca.maper.livro.LivroMapper;
import com.weg.SistemaBiblioteca.model.Livro;
import com.weg.SistemaBiblioteca.repository.LivroDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroDAO repository;
    private final LivroMapper mapper;

    public LivroService(LivroDAO repository, LivroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoLivroRespostaDTO salvarLivro(CriacaoLivroRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraRespostaDto(repository.salvar(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<CriacaoLivroRespostaDTO> listarLivros() throws SQLException{
        return repository.buscarTodos().stream()
                .map(mapper::paraRespostaDto)
                .toList();
    }

    public CriacaoLivroRespostaDTO buscarLivroPorId(int id) throws SQLException{
        Livro livro = repository.buscarPorId(id);

        if(livro == null){
            throw new LivroNaoExisteException();
        }
        return mapper.paraRespostaDto(livro);
    }

    public CriacaoLivroRespostaDTO atualizarLivro(int id, CriacaoLivroRequisicaoDTO requisicaoDTO) throws SQLException{
        Livro livro = repository.buscarPorId(id);

        if(livro == null){
            throw new LivroNaoExisteException();
        }

        return mapper.paraRespostaDto(repository.atualizar(mapper.paraEntidade(requisicaoDTO), id));
    }

    public void deletarLivro(int id) throws SQLException{
        if(!repository.livroExiste(id)){
            throw new LivroNaoExisteException();
        }

         repository.deletar(id);
    }
}
