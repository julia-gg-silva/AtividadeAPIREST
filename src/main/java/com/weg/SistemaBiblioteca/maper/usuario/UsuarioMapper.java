package com.weg.SistemaBiblioteca.maper.usuario;

import com.weg.SistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDTO;
import com.weg.SistemaBiblioteca.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {
    public Usuario paraEntidade(CriacaoUsuarioRequisicaoDTO requisicaoDTO){
        return new Usuario(requisicaoDTO.nome(), requisicaoDTO.email());
    }

    public CriacaoUsuarioRespostaDTO paraRespostaDto(Usuario usuario) {
        return new CriacaoUsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

}
