package com.weg.SistemaBiblioteca.exceptions;

public class EmprestimoNaoExisteException extends RuntimeException{

    public EmprestimoNaoExisteException(){
        super("Emprestimo não existe!");
    }
}
