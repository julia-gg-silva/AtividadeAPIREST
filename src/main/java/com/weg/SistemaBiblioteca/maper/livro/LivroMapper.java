package com.weg.SistemaBiblioteca.maper.livro;

import com.weg.SistemaBiblioteca.dto.livro.CriacaoLivroRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.livro.CriacaoLivroRespostaDTO;
import com.weg.SistemaBiblioteca.model.Livro;
import com.weg.SistemaBiblioteca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro paraEntidade(CriacaoLivroRequisicaoDTO requisicaoDTO){
        return new Livro(requisicaoDTO.titulo(), requisicaoDTO.autor(), requisicaoDTO.ano_publicacao());
    }

    public CriacaoLivroRespostaDTO paraRespostaDto(Livro livro){
        return new CriacaoLivroRespostaDTO(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno_publicacao());
    }
}
