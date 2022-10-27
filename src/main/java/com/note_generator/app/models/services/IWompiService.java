package com.note_generator.app.models.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.note_generator.app.models.dto.PayMethodDTO;
import com.note_generator.app.models.dto.TransactionDTO;

public interface IWompiService {
    public String getAcceptanceToken();
    public JsonNode createPaySource(PayMethodDTO payMethodDTO);
    public JsonNode CreateTransaction(TransactionDTO transactionDTO);
}
