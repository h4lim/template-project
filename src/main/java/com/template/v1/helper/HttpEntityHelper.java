package com.template.v1.helper;

import com.template.v1.domain.vo.KasproMerchantToken;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpEntityHelper {

    private HttpEntityHelper() {

    }

    public static @NotNull HttpEntity<?> setHttpEntity(KasproMerchantToken.Request request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }

}
