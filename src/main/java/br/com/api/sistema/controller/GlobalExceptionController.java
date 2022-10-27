package br.com.api.sistema.controller;

import br.com.api.sistema.exception.BadRequestException;
import br.com.api.sistema.exception.ErrorMessage;
import br.com.api.sistema.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    /**
     * Método para startar a classe de BadRequestException
     * @param exception
     * @return instância de Bad Request
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorMessage> badRequest(BadRequestException exception) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getErro(), exception.getDescricao()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Método para startar a classe de NotFoundException
     * @param exception
     * @return instância de Not Found
     */
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFound(NotFoundException exception) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getErro(), exception.getDescricao()), HttpStatus.NOT_FOUND);
    }
}
