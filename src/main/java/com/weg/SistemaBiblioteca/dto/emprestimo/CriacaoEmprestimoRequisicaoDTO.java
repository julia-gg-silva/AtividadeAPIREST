package com.weg.SistemaBiblioteca.dto.emprestimo;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record CriacaoEmprestimoRequisicaoDTO(
        int livro_id,
        int usuario_id,
        LocalDate data_emprestimo,
        LocalDate data_devolucao
) {
}
