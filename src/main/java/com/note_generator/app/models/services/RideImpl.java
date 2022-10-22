package com.note_generator.app.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note_generator.app.models.dao.RideDao;
import com.note_generator.app.models.entity.Location;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.entity.User;

@Service
public class RideImpl implements IRide {

	@Autowired
	private RideDao rideDao;

	@Autowired
	private IUser IUser;

	@Override
	public Boolean initRide(Integer userId, Location location) {

		List<User> users = new ArrayList<>();;
		Boolean response = false;
		try {
			User user = IUser.getUser(userId);
			if (user.getRole().equals("rider")) {
				users.add(user);

				Ride ride = new Ride();
				ride.setLocation(location);
				ride.setStatus("inited");
				ride.setUser(users);
				saveRide(ride);

				response = true;
			}
		}
		catch(Exception e){
			response = false;
		}
		
		return response;

	}

	@Override
	public Boolean finishRide(Integer userId, Integer rideId) {

		Boolean finished = false;
		try {
			Ride ride = rideDao.findById(rideId).orElse(null);
			for (User user : ride.getUser()) {
				if (user.getId() == userId && user.getRole() == "driver") {
					ride.setStatus("finished");
					ride.setUpdateAt(new Date());
					finished = true;
					this.saveRide(ride);
				}
			}
		} catch (Exception e) {
			finished = false;
		}
		return finished;

	}

	@Override
	public void saveRide(Ride ride) {
		rideDao.save(ride);
	}

}
