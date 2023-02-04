package com.template.common.exception;

import com.template.common.constant.ConstantCollection;
import com.template.common.helper.RestMessage;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class RestTemplateExceptionHandler extends RuntimeException implements Serializable {

    public final transient RestMessage<?,?> restMessage;
    public final ConstantCollection.EnumApiName apiName;
    public final ConstantCollection.EnumServiceName serviceName;
    public final String responseBody;
    public final HttpStatus httpStatus;

    public RestTemplateExceptionHandler(RestMessage<?,?> restMessage, ConstantCollection.EnumApiName apiName,
                                        ConstantCollection.EnumServiceName serviceName, String responseBody, HttpStatus httpStatus) {
        this.restMessage = restMessage;
        this.apiName = apiName;
        this.serviceName = serviceName;
        this.responseBody = responseBody;
        this.httpStatus = httpStatus;
    }

}
