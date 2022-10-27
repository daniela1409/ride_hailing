package com.note_generator.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.note_generator.app.models.dao.PayMethodDao;
import com.note_generator.app.models.dto.PayMethodDTO;
import com.note_generator.app.models.dto.TransactionDTO;

@Service
public class WompiServiceImpl implements IWompiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.external.service.base-url}")
    private String baseUrl;

    @Value("${spring.external.service.public-key}")
    private String publicKey;

    @Value("${spring.external.service.private-key}")
    private String privateKey;

    @SuppressWarnings("null")
    public String getAcceptanceToken() {
        String fullPath = baseUrl + "/merchants/{publicKey}";
        String acceptance_token = restTemplate.getForEntity(fullPath, JsonNode.class, publicKey).getBody().get("data")
                .get("presigned_acceptance").get("acceptance_token").asText();

        return acceptance_token;
    }

    @SuppressWarnings("null")
    public JsonNode createPaySource(PayMethodDTO payMethodDTO) {
        String fullPath = baseUrl + "/payment_sources";

        payMethodDTO.setAcceptance_token(getAcceptanceToken());

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Bearer " + privateKey);

        HttpEntity<?> httpEntity = new HttpEntity<Object>(payMethodDTO, requestHeaders);

        JsonNode payMethod = restTemplate.exchange(fullPath, HttpMethod.POST, httpEntity, JsonNode.class).getBody()
                .get("data");

        return payMethod;
    }

    @SuppressWarnings("null")
    @Override
    public JsonNode CreateTransaction(TransactionDTO transactionDTO) {
        String fullPath = baseUrl + "/transactions";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Bearer " + privateKey);
        String requestJson = "{\"amount_in_cents\": " + transactionDTO.getAmount_in_cents() +
                ", \"currency\": \"" + transactionDTO.getCurrency() + "\"" +
                ", \"customer_email\": " + "\"jedagos@gamil.com\" " +
                ", \"payment_method\": {\"installments\": " + transactionDTO.getInstallments() + "}" +
                ", \"reference\" : \"" + transactionDTO.getReference() + "\"" +
                ", \"payment_source_id\": " + transactionDTO.getPayment_source_id() + "}";

        HttpEntity<?> httpEntity = new HttpEntity<String>(requestJson, requestHeaders);
        JsonNode transaction = restTemplate.exchange(fullPath, HttpMethod.POST, httpEntity, JsonNode.class).getBody()
                .get("data");
        
        return transaction;
    }

}
