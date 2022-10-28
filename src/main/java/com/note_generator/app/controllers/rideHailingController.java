package com.note_generator.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.note_generator.app.models.dto.LocationDTO;
import com.note_generator.app.models.dto.TransactionDTO;
import com.note_generator.app.models.entity.Location;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.entity.Transaction;
import com.note_generator.app.models.entity.User;
import com.note_generator.app.models.services.ILocation;
import com.note_generator.app.models.services.IRide;
import com.note_generator.app.models.services.ITransaction;
import com.note_generator.app.models.services.IWompiService;

@RestController
@RequestMapping("api/v1")
public class rideHailingController {

    @Autowired
    private ILocation iLocation;

    @Autowired
    private IRide iRide;

    @Autowired
    private IWompiService iWompiService;

    @Autowired
    private ITransaction iTransaction;

    @PostMapping(value = "/ride/init/{userId}")
    public String initRide(@Valid @RequestBody LocationDTO location, BindingResult result,
            @PathVariable Integer userId) {

        Location locationCreated = null;
        String response = "";

        try {
            if (result.hasErrors()) {
                throw new RuntimeException("There is error");
            }

            locationCreated = iLocation.saveLocation(location);
            Ride ride = iRide.initRide(userId, locationCreated);

            if (ride == null) {
                throw new RuntimeException("Ride already inited");
            }
            response = "Number of ride is " + ride.getId();

        } catch (RuntimeException e) {
            response = e.getMessage();
        }

        return response;

    }

    @PutMapping(value = "/ride/finish/{userId}/{rideId}")
    public String finishRide(@PathVariable Integer userId, @PathVariable Integer rideId,
            @RequestBody Map<String, String> data) {

        String response = "";

        try {
            TransactionDTO transactionDTO = new TransactionDTO();
            Integer kms = Integer.valueOf(data.get("km"));
            Integer minutes = Integer.valueOf(data.get("minutes"));
            Integer installments = Integer.valueOf(data.get("installments"));
            String reference = (data.get("reference"));

            if (reference.equals("")) {
                throw new RuntimeException("Reference is a required space");
            }

            transactionDTO.setAmount_in_cents(kms, minutes);
            transactionDTO.setReference(reference);
            transactionDTO.setInstallments(installments);
            transactionDTO.setCurrency("COP");

            User user = iRide.finishRide(userId, rideId);

            JsonNode transaction = iWompiService.createTransaction(transactionDTO, user);

            Transaction transactionCreated = iTransaction.saveTransaction(transactionDTO,
                    transaction.get("id").asText(), user.getRide(), user.getPayMethod());
            
            if (transactionCreated == null) {
                throw new RuntimeException("Something is bad. Try again");
            }
            
            response = "The number of transaction is " + transactionCreated.getId();
            
            
        } catch (RuntimeException e) {
            response = e.getMessage();
        }

        return response;
    }

}
