package com.note_generator.app.models.dto;

import javax.validation.constraints.NotEmpty;

public class PayMethodDTO {

    @NotEmpty
    private String type;

    @NotEmpty
    private String token;

    @NotEmpty
    private String customer_email;

    private String acceptance_token;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String custome_email) {
        this.customer_email = custome_email;
    }

    public String getAcceptance_token() {
        return acceptance_token;
    }

    public void setAcceptance_token(String aceptance_token) {
        this.acceptance_token = aceptance_token;
    }

}
