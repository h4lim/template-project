package com.template.common.domain.contract;

import com.template.common.domain.vo.ClientApiInstance;
import com.template.common.domain.vo.MessageInstance;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class TemplateConfigContract {

    public interface ITemplateConfig {
        MessageInstance getMessage() throws IOException;

        RestTemplate restTemplate();

        HttpHeaders httpHeaders();

        ClientApiInstance clientApiInstance();
    }

}
