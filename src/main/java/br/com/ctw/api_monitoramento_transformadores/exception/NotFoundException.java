package br.com.ctw.api_monitoramento_transformadores.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
