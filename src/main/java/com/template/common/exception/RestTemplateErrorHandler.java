package com.template.common.exception;

import com.template.common.constant.ConstantCollection;
import com.template.common.helper.RestMessage;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

    private final RestMessage<?, ?> restMessage;
    private final ConstantCollection.EnumApiName apiName;
    private final ConstantCollection.EnumServiceName serviceName;

    public RestTemplateErrorHandler(RestMessage<?, ?> restMessage, ConstantCollection.EnumApiName apiName, ConstantCollection.EnumServiceName serviceName) {
        this.restMessage = restMessage;
        this.apiName = apiName;
        this.serviceName = serviceName;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
                String responseBody = reader.lines().collect(Collectors.joining(""));
                throw new RestTemplateExceptionHandler(this.restMessage,
                        this.apiName, this.serviceName, responseBody, response.getStatusCode());
            }
        }
    }

}
