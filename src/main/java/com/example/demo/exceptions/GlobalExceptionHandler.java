package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. ERRO 404: Recurso não encontrado no banco
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResponse handleNotFound(RecursoNaoEncontradoException ex) {
        return new ErroResponse(ex.getMessage(), 404);
    }

    // 2. ERRO 400: Cliente mandou JSON quebrado, tipo errado na URL ou dado inválido
    @ExceptionHandler({
            IllegalArgumentException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse handleBadRequest(Exception ex) {
        return new ErroResponse("Dados inválidos na requisição: " + ex.getMessage(), 400);
    }

    // 3. ERRO 500: Pega QUALQUER outra exceção (RuntimeException, SQL, NullPointer, etc.)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResponse handleGeneralException(Exception ex) {
        return new ErroResponse(ex.getMessage(), 500);
    }

}