package com.weg.SistemaBiblioteca.dto.dataDevDTO;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record DataDevRequisicaoDTO(
        LocalDate dataDevolucao
) {
}
