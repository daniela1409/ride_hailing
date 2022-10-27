package com.note_generator.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.note_generator.app.models.dto.PayMethodDTO;
import com.note_generator.app.models.dto.TransactionDTO;
import com.note_generator.app.models.entity.PayMethod;
import com.note_generator.app.models.entity.User;
import com.note_generator.app.models.services.IPayMethods;
import com.note_generator.app.models.services.IUser;
import com.note_generator.app.models.services.IWompiService;

@RestController
@RequestMapping("/api/v1")
public class PaysController {

    @Autowired
    private IWompiService iWompiService;

    @Autowired
    private IUser iUser;

    @Autowired
    private IPayMethods iPayMethod;

    @GetMapping("/get")
    public String get() {

//        TransactionDTO transactionDTO = new TransactionDTO();
//
//        transactionDTO.setAmount_in_cents(500000);
//        transactionDTO.setCurrency("COP");
//        transactionDTO.setInstallments(2);
//        transactionDTO.setReference("2322er3234ed4");
//        transactionDTO.setPayment_source_id(42402);
//
//        JsonNode pm = iWompiService.CreateTransaction(transactionDTO);
//
//        return pm;
        return "";
    }

    @SuppressWarnings("unused")
    @PostMapping("/{userId}")
    public String createPayMethod(@Valid @RequestBody PayMethodDTO payMethodDTO, BindingResult result,
            @PathVariable Integer userId) {

        String response = "";
        User user = null;

        try {
            if (result.hasErrors()) {
                throw new RuntimeException("There is error");
            }

            user = iUser.getUser(userId);

            if (user == null) {
                throw new RuntimeException("User does not exist");
            }

            if (!user.getRole().equals("rider")) {
                throw new RuntimeException("User is not a rider");
            }

            JsonNode payMethodDTOCreated = iWompiService.createPaySource(payMethodDTO);

            PayMethod payMethod = iPayMethod.savePayMethod(payMethodDTO, payMethodDTOCreated.get("id").asInt(),
                    payMethodDTOCreated.get("status").asText(), user);

            response = payMethodDTOCreated.get("id").asText();

        } catch (RuntimeException e) {
            response = e.getMessage();
        }

        return response;
    }
}
