package com.template.common.domain.model;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.io.Serializable;

public class HttpSpec implements Serializable {

    private String logID;

    private String url;

    private HttpEntity httpEntity;

    private HttpMethod httpMethod;


    public HttpSpec(String logID, String url, HttpMethod httpMethod, HttpEntity httpEntity) {
        this.logID = logID;
        this.url = url;
        this.httpMethod = httpMethod;
        this.httpEntity = httpEntity;
    }

    public String getLogID() {
        return logID;
    }

    public String getUrl() {
        return url;
    }

    public HttpEntity getHttpEntity() {
        return httpEntity;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }
}
