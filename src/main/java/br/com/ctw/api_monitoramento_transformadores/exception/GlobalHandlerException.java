package br.com.ctw.api_monitoramento_transformadores.exception;

import br.com.ctw.api_monitoramento_transformadores.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalHandlerException
{
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException> handlerNotFound(
            NotFoundException ex, HttpServletRequest request
    )
    {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TransformadorJaExiste.class)
    public ResponseEntity<TransformadorJaExiste> handlerAlreadyExists(
            TransformadorJaExiste ex,HttpServletRequest request
    )
    {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Número de série já cadastrado",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().build();
    }
}
