package com.note_generator.app.models.dto;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionDTO {

    private Integer amount_in_cents;
    private String currency;
    private String reference;
    private Integer installments;
    private Integer payment_source_id;

    public Integer getAmount_in_cents() {
        return amount_in_cents;
    }
    
    public void setAmount_in_cents(Integer kms, Integer time) {
        this.amount_in_cents =  3500 + (kms*1000) + (time*200);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Integer getPayment_source_id() {
        return payment_source_id;
    }

    public void setPayment_source_id(Integer payment_source_id) {
        this.payment_source_id = payment_source_id;
    }


}
