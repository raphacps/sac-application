package com.m4u.simpsons.homer.api.exception;

import com.m4u.simpsons.homer.application.exception.SystemException;
import com.m4u.simpsons.homer.domain.model.transacao.TransacaoException;
import com.m4u.simpsons.homer.infrastructure.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

import static java.util.stream.Collectors.joining;

/**
 * Created by raphael on 24/03/17.
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<String> handleSystemException(SystemException se) {
        String errosJson = getJsonValues(se.motivo().codigo().asString(), se.motivo().mensagem().asString());
        log.warn(se.getClass() + " - " + errosJson);
        return ResponseEntity.status(getStatusCode(se)).body(errosJson);
    }

    @ExceptionHandler(TransacaoException.class)
    public ResponseEntity<String> handleTransacaoException(TransacaoException se) {
        //TODO rever se todos os erros de transacao devem ser tratados como 500
        String errosJson = getJsonValues(se.getErrorTO().getCode(), se.getErrorTO().getMessage());
        log.warn(se.getClass() + " - " + errosJson);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errosJson);
    }

    private HttpStatus getStatusCode(SystemException se) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(se.getClass(), ResponseStatus.class);
        if (responseStatus == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            return responseStatus.code();
        }
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<String> handleServletRequestBindingException(ServletRequestBindingException e) {
        String errosJson = getJsonValues(HttpStatus.BAD_REQUEST.name(), e.getMessage());
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errosJson);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String errosJson = getJsonValues(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        log.error("", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errosJson);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException iaex) {
        String errosJson = getJsonValues(HttpStatus.UNPROCESSABLE_ENTITY.name(), iaex.getMessage());
        log.warn(iaex.getClass() + " - " + errosJson);
        return ResponseEntity.unprocessableEntity().body(errosJson);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errosJson = getJsonValues(HttpStatus.BAD_REQUEST.name(), e.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> (FieldError) objectError)
                .map(fieldError -> fieldError.getField().concat(" ").concat(fieldError.getDefaultMessage()))
                .collect(joining(", ")));
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errosJson);
    }

    public String getJsonValues(String codigo, String mensagem) {
        HashMap<String, String> erros = new HashMap<>();
        erros.put("codigo", codigo);
        erros.put("mensagem", mensagem);
        return JsonUtil.toJson(erros);
    }
}
