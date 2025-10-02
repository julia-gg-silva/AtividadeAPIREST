package com.weg.SistemaBiblioteca.exceptions;

public class LivroNaoExisteException extends RuntimeException{

    public LivroNaoExisteException(){
        super("Livro não existe");
    }
}
