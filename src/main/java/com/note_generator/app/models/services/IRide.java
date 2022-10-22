package com.note_generator.app.models.services;

import com.note_generator.app.models.entity.Location;
import com.note_generator.app.models.entity.PayMethod;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.entity.User;

public interface IRide {

	public Boolean initRide(Integer userId, Location location);
	public void saveRide(Ride ride);
	public Boolean finishRide(Integer userId, Integer rideId);
	
}
