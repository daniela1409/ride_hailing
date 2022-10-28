package com.note_generator.app.models.services;

import com.note_generator.app.models.entity.Location;
import com.note_generator.app.models.entity.PayMethod;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.entity.User;

public interface IRide {

	public Ride initRide(Integer userId, Location location);
	public Ride saveRide(Ride ride);
	public User finishRide(Integer userId, Integer rideId);
	public User findAUserFree();
	
}
