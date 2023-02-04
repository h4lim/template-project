package com.template.common.builder;

import com.template.common.domain.model.HttpSpec;
import com.template.common.domain.contract.BaseContract;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class HttpClientBuilder {

    private final String logID;
    private final String url;
    private final HttpMethod httpMethod;
    private final HttpEntity<?> httpEntity;
    private final BaseContract.IBaseApi iBaseApi;

    public HttpClientBuilder(String logID, String url, HttpMethod httpMethod, HttpEntity<?> httpEntity, BaseContract.IBaseApi iBaseApi) {
        this.logID = logID;
        this.url = url;
        this.httpMethod = httpMethod;
        this.httpEntity = httpEntity;
        this.iBaseApi = iBaseApi;
    }

    public ResponseEntity<?> buildHttpClient() {
        return iBaseApi.hitClient(new HttpSpec(logID, url, httpMethod, httpEntity));
    }
}
