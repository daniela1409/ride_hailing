package com.note_generator.app.models.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.note_generator.app.models.dto.PayMethodDTO;
import com.note_generator.app.models.dto.TransactionDTO;
import com.note_generator.app.models.entity.User;

public interface IWompiService {
    public String getAcceptanceToken();
    public JsonNode createPaySource(PayMethodDTO payMethodDTO);
    public JsonNode createTransaction(TransactionDTO transactionDTO, User user);
    public String seeStatusTransaction(String transactionId);
}
