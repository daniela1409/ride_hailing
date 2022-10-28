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
    private IUser iUser;

    @Override
    public Ride initRide(Integer userId, Location location) {

        List<User> users = new ArrayList<>();
        User rider = iUser.getUser(userId);
        User driver = this.findAUserFree();

        if (rider == null) {
            throw new RuntimeException("User doesn't exist");
        }
        if (driver == null) {
            throw new RuntimeException("Drivers are not available");
        }

        if (!rider.getRole().equals("rider")) {
            throw new RuntimeException("Check role of user");
        }
        
        if(rider.getStatus().equals("init_ride")) {
            throw new RuntimeException("User already in ride");
        }

        users.add(rider);
        users.add(driver);

        Ride ride = new Ride();
        ride.setLocation(location);
        ride.setStatus("inited");
        ride.setUser(users);
        saveRide(ride);

        rider.setRide(ride);
        rider.setStatus("init_ride");
        driver.setRide(ride);
        driver.setLastService(new Date());
        driver.setStatus("busy");
        iUser.save(rider);
        iUser.save(driver);

        return ride;

    }

    @Override
    public User finishRide(Integer userId, Integer rideId) {

        Ride ride = rideDao.findById(rideId).orElse(null);
        User rider = null;

        if (ride == null) {
            throw new RuntimeException("Rite does not exist");
        }
        if (ride.getStatus().equals("finished")) {
            throw new RuntimeException("Rite was already done");
        }
        
        for (User user : ride.getUser()) {
            if (user.getId() == userId ) {
                if(user.getRole().equals("driver")) {
                    ride.setStatus("finished");
                    ride.setUpdateAt(new Date());
                    this.saveRide(ride);

                    user.setStatus("free");
                    iUser.save(user);
                }
            }
            if(user.getRole().equals("rider")) {
                user.setStatus("finish_ride");
                rider = user;
                iUser.save(user);
            }
        }

        return rider;
    }

    @Override
    public Ride saveRide(Ride ride) {
        return rideDao.save(ride);
    }

    @Override
    public User findAUserFree() {

        List<User> users = iUser.getDriverFree();
        Integer usersSize = users.size();
        User user = null;

        if (usersSize != 0) {
            user = users.get(0);
        }

        return user;
    }

}
