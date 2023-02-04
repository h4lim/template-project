package com.template.common.api;

import com.template.common.constant.ConstantCollection;
import com.template.common.domain.contract.BaseContract;
import com.template.common.domain.model.HttpSpec;
import com.template.common.exception.RestTemplateErrorHandler;
import com.template.common.helper.RestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service()
public class BaseApi<T, E> implements BaseContract.IBaseApi {

    private final RestMessage<T, E> restMessage;
    private RestTemplate restTemplate;
    private ConstantCollection.EnumServiceName serviceName;
    private ConstantCollection.EnumApiName apiName;

    @Autowired
    public BaseApi(RestTemplate restTemplate, RestMessage<T, E> restMessage) {
        this.restTemplate = restTemplate;
        this.restMessage = restMessage;
    }

    public void setExceptionHandler(ConstantCollection.EnumServiceName serviceName, ConstantCollection.EnumApiName apiName) {
        this.serviceName = serviceName;
        this.apiName = apiName;
    }

    public ResponseEntity<?> hitClient(HttpSpec httpSpec) {
        if (this.serviceName != null && this.apiName != null) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateErrorHandler(
                    this.restMessage, this.apiName, this.serviceName)).build();
        }
        return restTemplate.exchange(
                httpSpec.getUrl(), httpSpec.getHttpMethod(), httpSpec.getHttpEntity(), String.class);
    }

}
