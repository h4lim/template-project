package com.template.common.domain.contract;

import com.template.common.constant.ConstantCollection;
import com.template.common.domain.model.HttpSpec;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Locale;

public class BaseContract {

    public interface IBaseDelivery {
    }

    public interface IBaseApi {
        void setExceptionHandler(ConstantCollection.EnumServiceName serviceName, ConstantCollection.EnumApiName apiName);

        ResponseEntity<?> hitClient(HttpSpec httpSpec);
    }

    public interface IHttpClientHelper {
        ResponseEntity<?> hitClient(String logID, String url, HttpMethod httpMethod, HttpEntity<?> httpEntity);

        ResponseEntity<?> hitClient(String logID, String url, HttpMethod httpMethod, HttpEntity<?> httpEntity,
                                    ConstantCollection.EnumServiceName serviceName, ConstantCollection.EnumApiName apiName);
    }

    public interface IRestMessage<T, E> {
        ResponseEntity<T> buildResponse(String responseCode, Locale locale);

        ResponseEntity<T> buildResponse(String responseCode);

        ResponseEntity<T> buildResponse(String responseCode, E data);

        ResponseEntity<T> buildResponse(HttpStatus httpStatus, Locale locale, String responseCode, boolean responseStatus);

        ResponseEntity<T> buildResponse(HttpStatus httpStatus, String responseCode, String responseMessage, boolean responseStatus);
    }

}
