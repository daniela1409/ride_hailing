package com.note_generator.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note_generator.app.models.dto.LocationDTO;
import com.note_generator.app.models.entity.Location;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.entity.User;
import com.note_generator.app.models.services.ILocation;
import com.note_generator.app.models.services.IRide;
import com.note_generator.app.models.services.IUser;

@RestController
public class rideHailingController {

	@Autowired
	private ILocation iLocation;

	@Autowired
	private IRide iRide;
	
	@Autowired
	private IUser iUser;

	@PostMapping(value = "/ride/init/{userId}")
	public String initRide(@Valid @RequestBody LocationDTO location, BindingResult result,
			@PathVariable Integer userId) {

		Location locationCreated = null;
		String response = "";

		try {
			if (result.hasErrors()) {
				response = "There is error";
			}

			locationCreated = iLocation.saveLocation(location);
			Boolean ride = iRide.initRide(userId, locationCreated);

			if (!ride) {
				response = "Ride inited";
			}
		} catch (RuntimeException e) {
			response = e.getMessage();
		}

		return response;

	}

	@PutMapping(value = "/ride/finish/{userId}/{rideId}")
	public String finishRide(@PathVariable Integer userId, @PathVariable Integer rideId) {
	            
	    String response = "";
	    
	    
        try 
        { 
            Boolean wasFinished = iRide.finishRide(userId, rideId);
            
            if(wasFinished) {
                response = "Ride finished";
            }
            
        }  
        catch (RuntimeException e)  
        { 
            response = e.getMessage(); 
        }
	    
		return response;
	}
	
}
