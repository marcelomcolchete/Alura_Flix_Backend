package br.com.aluraflix.exception.handler;

import br.com.aluraflix.dto.ErrorMessageDTO;
import br.com.aluraflix.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> notFoundHandler(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

}
