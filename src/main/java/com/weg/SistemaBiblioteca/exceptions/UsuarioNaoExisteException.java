package com.weg.SistemaBiblioteca.exceptions;

public class UsuarioNaoExisteException extends RuntimeException{
    public UsuarioNaoExisteException(){
        super("Usuário não existe!");
    }
}
