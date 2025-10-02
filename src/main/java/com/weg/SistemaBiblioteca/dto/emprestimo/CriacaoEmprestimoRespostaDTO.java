package com.weg.SistemaBiblioteca.dto.emprestimo;

import java.time.LocalDate;

public record CriacaoEmprestimoRespostaDTO(
        int id,
        int livro_id,
        int usuario_id,
        LocalDate data_emprestimo,
        LocalDate data_devolucao
) {
}
