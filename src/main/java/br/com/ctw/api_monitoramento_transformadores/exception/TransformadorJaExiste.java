package br.com.ctw.api_monitoramento_transformadores.exception;

public class TransformadorJaExiste extends RuntimeException {
    public TransformadorJaExiste(String message) {
        super(message);
    }
}
