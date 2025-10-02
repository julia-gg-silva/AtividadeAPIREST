package com.weg.SistemaBiblioteca.dto.livro;

public record CriacaoLivroRequisicaoDTO(
        String titulo,
        String autor,
        int ano_publicacao
) {
}
