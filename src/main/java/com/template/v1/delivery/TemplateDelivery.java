package com.template.v1.delivery;

import com.template.common.helper.RestMessage;
import com.template.v1.domain.contract.TemplateContract;
import com.template.v1.domain.model.Person;
import com.template.v1.domain.vo.KasproMerchantToken;
import com.template.v1.domain.vo.PersonRequest;
import com.template.v1.helper.ModelHelper;
import com.template.v1.repository.IPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
public class TemplateDelivery<T, E> implements TemplateContract.ITemplateDelivery {

    private final RestMessage<T, E> restMessage;
    private final TemplateContract.IPersonService iPersonService;
    private final TemplateContract.IKasproService iKasproService;
    private IPersonDao iPersonDao;


    @Autowired
    public TemplateDelivery(RestMessage<T, E> restMessage,
                            TemplateContract.IPersonService iPersonService, TemplateContract.IKasproService iKasproService, IPersonDao iPersonDao) {
        this.restMessage = restMessage;
        this.iPersonService = iPersonService;
        this.iKasproService = iKasproService;
        this.iPersonDao = iPersonDao;
    }

    @PostMapping("/create_person")
    public ResponseEntity<T> createPerson(
            @RequestHeader HttpHeaders headers, @RequestBody PersonRequest.CreateRequest requestBody) {
        iPersonService.save(ModelHelper.setModel(requestBody));
        return restMessage.buildResponse("0");
    }

    @GetMapping("/find_person")
    public ResponseEntity<T> findPerson(@RequestHeader HttpHeaders headers,
                                        @RequestParam String msisdn) {
        Person person = iPersonService.getPerson(msisdn);
        return restMessage.buildResponse("0", (E) person);
    }

    @PostMapping("/update_person")
    public ResponseEntity<T> updatePerson(@RequestHeader HttpHeaders headers,
                                          @RequestBody PersonRequest.CreateRequest requestBody) {
        iPersonService.update(ModelHelper.setModel(requestBody));
        return restMessage.buildResponse("0");
    }

    @DeleteMapping("/delete_person")
    public ResponseEntity<T> deletePerson(@RequestHeader HttpHeaders headers, @RequestBody String msisdn) {
        iPersonService.delete(msisdn);
        return restMessage.buildResponse("0");
    }


    @GetMapping("/test")
    public ResponseEntity<T> test(@RequestHeader HttpHeaders headers) {
       /* SampleResponse data = new SampleResponse();
        data.setMsisdn("081398458855");
        data.setName("Halim");*/
        /* return restMessage.buildResponse("0", (E) data);*/
       /* KasproMerchantToken.Request request = new KasproMerchantToken.Request();
        request.setRequestId("1231313123");
        request.setMobileNumber("081398458855");
        request.setType("cashout");
        HttpEntity httpEntity = ClientSpecHelper.setHttpEntity(request);
        String url = "https://transaction2.dab-partner.id/customer/merchant/token";
        String data = httpClientHelper.hitClient(request.getMobileNumber(), url, HttpMethod.POST, httpEntity);
        return restMessage.buildResponse("0",  data);*/

        KasproMerchantToken.Request request = new KasproMerchantToken.Request();
        request.setRequestId("1231313123");
        request.setMobileNumber("0813984588556");
        request.setType("cashout");

        KasproMerchantToken.Response clientResponse = iKasproService.MerchantTokenService(request);

        return restMessage.buildResponse("0", (E) clientResponse);

    }


}
