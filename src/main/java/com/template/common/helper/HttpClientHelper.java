package com.template.common.helper;

import com.template.common.builder.HttpClientBuilder;
import com.template.common.domain.contract.BaseContract;
import com.template.common.constant.ConstantCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class HttpClientHelper implements BaseContract.IHttpClientHelper {

    private final BaseContract.IBaseApi iBaseApi;

    @Autowired
    public HttpClientHelper(BaseContract.IBaseApi iBaseApi) {
        this.iBaseApi = iBaseApi;
    }

    public ResponseEntity<?> hitClient(String logID, String url, HttpMethod httpMethod, HttpEntity<?> httpEntity) {
        return new HttpClientBuilder(logID, url, httpMethod, httpEntity, iBaseApi).buildHttpClient();
    }

    public ResponseEntity<?> hitClient(String logID, String url, HttpMethod httpMethod, HttpEntity<?> httpEntity,
                                    ConstantCollection.EnumServiceName serviceName, ConstantCollection.EnumApiName apiName) {
        iBaseApi.setExceptionHandler(serviceName, apiName);
        return new HttpClientBuilder(logID, url, httpMethod, httpEntity, iBaseApi).buildHttpClient();
    }

}

