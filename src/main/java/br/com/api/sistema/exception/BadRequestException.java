package br.com.api.sistema.exception;

public class BadRequestException extends AbstractException {

    public BadRequestException(String descricao) {
        super("BadRequest", descricao);
    }
}
