package com.weg.SistemaBiblioteca.service;

import com.weg.SistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.weg.SistemaBiblioteca.exceptions.UsuarioNaoExisteException;
import com.weg.SistemaBiblioteca.maper.usuario.UsuarioMapper;
import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.repository.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioDAO repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoUsuarioRespostaDTO criarUsuario(CriacaoUsuarioRequisicaoDTO requisicaoDTO) throws SQLException{
        return mapper.paraRespostaDto(repository.salvar(mapper.paraEntidade(requisicaoDTO)));
    }


    public List<CriacaoUsuarioRespostaDTO> listarUsuarios() throws SQLException{
        return repository.buscarTodos().stream()
                .map(mapper::paraRespostaDto)
                .toList();
    }

    public CriacaoUsuarioRespostaDTO buscarUsuarioPorId(int id) throws SQLException{
        Usuario usuario = repository.buscarPorId(id);

        if(usuario == null){
            throw  new UsuarioNaoExisteException();
        }
        return mapper.paraRespostaDto(usuario);
    }

    public CriacaoUsuarioRespostaDTO atualizarUsuario(int id, CriacaoUsuarioRequisicaoDTO requisicaoDTO) throws SQLException{
        Usuario usuario = repository.buscarPorId(id);

        if(usuario == null){
            throw new UsuarioNaoExisteException();
        }

        return mapper.paraRespostaDto(repository.atualizar(mapper.paraEntidade(requisicaoDTO), id));
    }

    public void deletarUsuario(int id) throws SQLException{
       if(!repository.usuarioExiste(id)){
           throw new UsuarioNaoExisteException();
       }
       repository.deletar(id);
    }
}
