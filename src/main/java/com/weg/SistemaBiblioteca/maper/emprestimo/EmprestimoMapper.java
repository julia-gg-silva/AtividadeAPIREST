package com.weg.SistemaBiblioteca.maper.emprestimo;

import com.weg.SistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDTO;
import com.weg.SistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDTO;
import com.weg.SistemaBiblioteca.dto.livro.CriacaoLivroRequisicaoDTO;
import com.weg.SistemaBiblioteca.model.Emprestimo;
import com.weg.SistemaBiblioteca.model.Livro;
import com.weg.SistemaBiblioteca.model.Usuario;
import com.weg.SistemaBiblioteca.service.EmprestimoService;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public Emprestimo paraEntidade(CriacaoEmprestimoRequisicaoDTO requisicaoDTO){
        return new Emprestimo(requisicaoDTO.livro_id(), requisicaoDTO.usuario_id(), requisicaoDTO.data_emprestimo(), requisicaoDTO.data_devolucao());
    }

    public CriacaoEmprestimoRespostaDTO paraRespostaDto(Emprestimo emprestimo){
        return new CriacaoEmprestimoRespostaDTO(emprestimo.getId(), emprestimo.getLivro_id(), emprestimo.getUsuario_id(), emprestimo.getData_emprestimo(), emprestimo.getData_devolucao());
    }

    public Emprestimo paraUpdate(CriacaoEmprestimoRequisicaoDTO requisicaoDTO, Emprestimo emprestimo){
        if((requisicaoDTO.livro_id() != emprestimo.getLivro_id() && requisicaoDTO.livro_id() != 0)){
            emprestimo.setLivro_id(requisicaoDTO.livro_id());
        }

        if((requisicaoDTO.usuario_id() != emprestimo.getUsuario_id() && requisicaoDTO.usuario_id() != 0)){
            emprestimo.setUsuario_id(requisicaoDTO.usuario_id());
        }

        if((requisicaoDTO.data_emprestimo() != emprestimo.getData_emprestimo() && requisicaoDTO.data_emprestimo() != null)){
            emprestimo.setData_emprestimo(requisicaoDTO.data_emprestimo());
        }

        if((requisicaoDTO.data_devolucao() != emprestimo.getData_devolucao() && requisicaoDTO.data_devolucao() != null)){
            emprestimo.setData_devolucao(requisicaoDTO.data_devolucao());
        }

        return  emprestimo;
    }
}
