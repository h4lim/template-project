package com.template.common.helper;

import com.template.common.builder.ResponseBuilder;
import com.template.common.domain.contract.BaseContract;
import com.template.common.domain.vo.MessageInstance;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class RestMessage<T, E> implements BaseContract.IRestMessage<T, E> {

    private final MessageInstance messageInstance;

    @Autowired
    public RestMessage(MessageInstance messageInstance) {
        this.messageInstance = messageInstance;
    }

    public ResponseEntity<T> buildResponse(String responseCode, Locale locale) {
        return new ResponseBuilder<T, E>(HttpStatus.OK,
                responseCode, getResponseMessage(locale, responseCode), true).buildResponse();
    }

    public ResponseEntity<T> buildResponse(String responseCode) {
        return new ResponseBuilder<T, E>(HttpStatus.OK,
                responseCode, getResponseMessage(Locale.ENGLISH, responseCode), true).buildResponse();
    }

    public ResponseEntity<T> buildResponse(String responseCode, E data) {
        return new ResponseBuilder<T, E>(HttpStatus.OK,
                responseCode, getResponseMessage(Locale.ENGLISH, responseCode), true).
                responseData(data).buildResponse();
    }

    public ResponseEntity<T> buildResponse(HttpStatus httpStatus, Locale locale, String responseCode, boolean responseStatus) {
        return new ResponseBuilder<T, E>(httpStatus,
                responseCode, getResponseMessage(locale, responseCode), responseStatus).buildResponse();
    }

    public ResponseEntity<T> buildResponse(HttpStatus httpStatus, String responseCode, String responseMessage, boolean responseStatus) {
        return new ResponseBuilder<T, E>(httpStatus,
                responseCode, responseMessage, responseStatus).buildResponse();
    }

    private String getResponseMessage(@NotNull Locale locale, String responseCode) {
        return locale.equals(Locale.ENGLISH) ? this.messageInstance.mapEN.get(responseCode)
                : this.messageInstance.mapID.get(responseCode);
    }
}
