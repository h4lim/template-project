package com.template.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RestTemplateExceptionHandler.class)
    public ResponseEntity<?> handleMyRestTemplateException(RestTemplateExceptionHandler e) throws JsonProcessingException {
        return kasproExceptionHandler(e);
    }

    private ResponseEntity<?> kasproExceptionHandler(@NotNull RestTemplateExceptionHandler e) throws JsonProcessingException {
        Map<String, Object> map = new ObjectMapper().readValue(e.responseBody, Map.class);
        String responseCode = map.get("code").toString();
        String responseMessage = map.get("message").toString();
        return e.restMessage.buildResponse(e.httpStatus, responseCode, responseMessage, false);
    }
}
