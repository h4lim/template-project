package com.template.common.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.template.common.builder.ResponseBuilder;

import java.sql.Timestamp;
import java.util.Date;

public class GenericResponse<E> {

    @JsonProperty("response_code")
    private String responseCode;

    @JsonProperty("response_message")
    private String responseMessage;

    @JsonProperty("response_status")
    private boolean responseStatus;

    @JsonProperty("response_timestamp")
    private Timestamp responseTimestamp;


    public GenericResponse(ResponseBuilder<?, E> responseBuilder) {
        setResponseMessage(responseBuilder.getResponseMessage());
        setResponseCode(responseBuilder.getResponseCode());
        setResponseStatus(responseBuilder.isResponseStatus());
        setResponseTimestamp(new Timestamp(new Date().getTime()));
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setResponseStatus(boolean responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void setResponseTimestamp(Timestamp responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

}
