package com.note_generator.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note_generator.app.models.entity.Location;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.services.ILocation;
import com.note_generator.app.models.services.IRide;

@RestController
@RequestMapping(value = "/")
public class rideHailingController {

	@Autowired
	private ILocation iLocation;

	@Autowired
	private IRide iRide;

	@PostMapping(value = "/ride/init/{userId}")
	public String initRide(@RequestBody Map<String, String> location, @PathVariable Integer userId) {

		Location locationCreated = null;
		String response = "";
		if (!location.get("latitude").equals("") && !location.get("longitude").equals("")) {
			locationCreated = iLocation.saveLocation(location);
			Boolean ride = iRide.initRide(userId, locationCreated);
			response = "Ride inited";
		} else {
			response = "Empty spaces";
		}

		return response;
	}
	
	@PutMapping(value = "/ride/finish/{userId}/{rideId}")
	public String finishRide(@PathVariable Integer userId, @PathVariable Integer rideId) {

		String response = "Holi";
		
		return response;
	}
}
