package br.com.bitway.bibliotech.handler;

import br.com.bitway.bibliotech.exceptions.ClienteNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ClienteNotFoundException> handlerBadRequestException(ClienteNotFoundException exception) {
        return ResponseEntity.badRequest().body(ClienteNotFoundException.builder()
                .build());
    }


}
