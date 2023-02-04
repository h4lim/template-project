package com.template.common.builder;

import com.template.common.domain.vo.GenericResponse;
import com.template.common.domain.vo.GenericResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder<T, E> {

    private final String responseCode;
    private final String responseMessage;
    private final boolean responseStatus;
    private final HttpStatus httpStatus;
    private E responseData;

    public ResponseBuilder(HttpStatus httpStatus, String responseCode, String responseMessage, boolean responseStatus) {
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseStatus = responseStatus;
    }

    public ResponseBuilder<T, E> responseData(E responseData) {
        this.responseData = responseData;
        return this;
    }

    public ResponseEntity<T> buildResponse() {
        if (getResponseData() != null) {
            return new ResponseEntity(new GenericResponseData<>(this), this.httpStatus);
        }
        return new ResponseEntity(new GenericResponse<>(this), this.httpStatus);
    }

    public String getResponseCode() {
        return responseCode;
    }

    public boolean isResponseStatus() {
        return responseStatus;
    }

    public E getResponseData() {
        return responseData;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
