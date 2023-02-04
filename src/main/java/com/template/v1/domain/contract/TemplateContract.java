package com.template.v1.domain.contract;

import com.template.v1.domain.model.Person;
import com.template.v1.domain.vo.KasproMerchantToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public class TemplateContract {

    public interface ITemplateDelivery {
        ResponseEntity test(@RequestHeader HttpHeaders headers);
    }

    public interface IPersonService {
        void save(Person data);

        void update(Person data);

        void delete(Person data);

        void delete(String msisdn);

        Person getPerson(Long id);

        Person getPerson(String mssidn);
    }

    public interface IKasproService<T, E> {
        KasproMerchantToken.Response MerchantTokenService(KasproMerchantToken.Request request);
    }

}
