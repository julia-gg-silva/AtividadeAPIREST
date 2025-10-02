package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.dto.dataDevDTO.DataDevRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDTO;
import com.weg.SistemaBiblioteca.exceptions.EmprestimoNaoExisteException;
import com.weg.SistemaBiblioteca.maper.emprestimo.EmprestimoMapper;
import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.repository.EmprestimoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoDAO repository;
    private final EmprestimoMapper mapper;


    public EmprestimoService(EmprestimoDAO repository, EmprestimoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoEmprestimoRespostaDTO criarEmprestimo(CriacaoEmprestimoRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraRespostaDto(repository.salvar(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<CriacaoEmprestimoRespostaDTO> listarEmprestimos() throws SQLException{
        return repository.buscarTodos().stream()
                .map(mapper::paraRespostaDto)
                .toList();
    }

    public CriacaoEmprestimoRespostaDTO buscarEmprestimoPorId(int id) throws SQLException{
        Emprestimo emprestimo = repository.buscarPorId(id);

        if(emprestimo == null){
            throw new EmprestimoNaoExisteException();
        }

        return mapper.paraRespostaDto(emprestimo);
    }

    public CriacaoEmprestimoRespostaDTO atualizarEmprestimo(int id, CriacaoEmprestimoRequisicaoDTO requisicaoDTO) throws SQLException{
        Emprestimo emprestimo = repository.buscarPorId(id);

        if(emprestimo == null){
            throw new EmprestimoNaoExisteException();
        }

        Emprestimo newEmprestimo = mapper.paraUpdate(requisicaoDTO, emprestimo);
        repository.atualizar(newEmprestimo);
        return mapper.paraRespostaDto(newEmprestimo);
    }

    public void atualizarDataDevolucao(int id, DataDevRequisicaoDTO requisicaoDTO) throws SQLException{
        if(!repository.emprestimoExiste(id)){
            throw  new EmprestimoNaoExisteException();
        }

        repository.atualizarDataDevolucao(id,requisicaoDTO.dataDevolucao());
    }

    public void deletarEmprestimo(int id) throws SQLException{
        if(!repository.emprestimoExiste(id)){
            throw new EmprestimoNaoExisteException();
        }

        repository.deletar(id);
    }
}
