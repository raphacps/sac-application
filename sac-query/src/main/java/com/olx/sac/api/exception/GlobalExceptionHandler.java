package com.olx.sac.api.exception;

import com.olx.sac.infrastructure.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

import static java.util.stream.Collectors.joining;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<String> handleServletRequestBindingException(ServletRequestBindingException e) {
        String jsonErrors = getJsonValues(HttpStatus.BAD_REQUEST.name(), e.getMessage());
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonErrors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String jsonErrors = getJsonValues(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        log.error("", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonErrors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException iaex) {
        String jsonErrors = getJsonValues(HttpStatus.UNPROCESSABLE_ENTITY.name(), iaex.getMessage());
        log.warn(iaex.getClass() + " - " + jsonErrors);
        return ResponseEntity.unprocessableEntity().body(jsonErrors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String jsonErrors = getJsonValues(HttpStatus.BAD_REQUEST.name(), e.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> (FieldError) objectError)
                .map(fieldError -> fieldError.getField().concat(" ").concat(fieldError.getDefaultMessage()))
                .collect(joining(", ")));
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonErrors);
    }

    public String getJsonValues(String code, String message) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("code", code);
        errors.put("message", message);
        return JsonUtil.toJson(errors);
    }
}
