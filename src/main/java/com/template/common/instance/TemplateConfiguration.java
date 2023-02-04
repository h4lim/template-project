package com.template.common.instance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.common.domain.contract.TemplateConfigContract;
import com.template.common.domain.vo.ClientApiInstance;
import com.template.common.domain.vo.MessageInstance;
import com.template.v1.domain.model.Client;
import com.template.v1.repository.IClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
public class TemplateConfiguration implements TemplateConfigContract.ITemplateConfig {

    @Value("classpath:messages.json")
    private Resource resource;

    private final IClientDao iClientDao;

    @Autowired
    public TemplateConfiguration(IClientDao iClientDao) {
        this.iClientDao = iClientDao;
    }

    @Bean
    @Scheduled(fixedDelay = 100000)
    public MessageInstance getMessage() throws IOException {
        Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        String jsonString = FileCopyUtils.copyToString(reader);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, String>> mapMessage = mapper.readValue(jsonString, Map.class);
        Map<String, String> mapEN = mapMessage.get("en_message");
        Map<String, String> mapID = mapMessage.get("id_message");
        return new MessageInstance(mapEN, mapID);
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        return new RestTemplate(factory);
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.ALL_VALUE);
        return headers;
    }

    @Bean
    @Scheduled(fixedDelay = 20000)
    public ClientApiInstance clientApiInstance() {
        List<Client> listOfClient = iClientDao.findAll();
        Map<String, String> mapClientApi = new HashMap<>();
        for (Client client : listOfClient) {
            mapClientApi.put(client.getClientName() + "_" + client.getApiName(),
                    client.getDomain() + client.getApiUrl());
        }
        return new ClientApiInstance(mapClientApi);
    }

}
