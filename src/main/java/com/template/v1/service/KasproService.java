package com.template.v1.service;

import com.google.gson.Gson;
import com.template.common.constant.ConstantCollection;
import com.template.common.domain.vo.ClientApiInstance;
import com.template.common.helper.HttpClientHelper;
import com.template.v1.domain.contract.TemplateContract;
import com.template.v1.domain.vo.KasproMerchantToken;
import com.template.v1.helper.HttpEntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service()
public class KasproService<T, E> implements TemplateContract.IKasproService<T, E> {

    private final HttpClientHelper httpClientHelper;
    private final ClientApiInstance clientApiInstance;

    @Autowired
    public KasproService(HttpClientHelper httpClientHelper, ClientApiInstance clientApiInstance) {
        this.httpClientHelper = httpClientHelper;
        this.clientApiInstance = clientApiInstance;
    }

    @Override
    public KasproMerchantToken.Response MerchantTokenService(KasproMerchantToken.Request request) {

        HttpEntity httpEntity = HttpEntityHelper.setHttpEntity(request);
        String url = clientApiInstance.mapClientApi.get("KASPRO_MERCHANT_TOKEN");
        ResponseEntity responseEntity = httpClientHelper.hitClient(
                request.getMobileNumber(), url, HttpMethod.POST, httpEntity,
                ConstantCollection.EnumServiceName.MERCHANT_TOKEN_SERVICE,
                ConstantCollection.EnumApiName.KASPRO);

        KasproMerchantToken.Response response = new Gson().fromJson(responseEntity.getBody().toString(), KasproMerchantToken.Response.class);


        return response;
    }
}
