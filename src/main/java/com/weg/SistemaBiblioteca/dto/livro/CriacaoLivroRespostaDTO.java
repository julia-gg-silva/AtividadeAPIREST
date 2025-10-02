package com.weg.SistemaBiblioteca.dto.livro;

public record CriacaoLivroRespostaDTO(
        int id,
        String titulo,

        String autor,
        int ano_publicacao
){
}
