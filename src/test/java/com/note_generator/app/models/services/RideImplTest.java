package com.note_generator.app.models.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.note_generator.app.models.dao.RideDao;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.entity.User;

@ExtendWith(MockitoExtension.class)
public class RideImplTest {
    
    @Mock
    private UserImpl userImpl;
    
    @Mock
    private RideDao rideDao;
    
    @Mock
    private RideImpl rideMock;
    
    @InjectMocks
    private RideImpl rideImpl;
    
    private User rider;
    
    private User driver;
    
    private Ride ride;
    
    @BeforeEach 
    void setUp()
    {
        List<User> users = new ArrayList<>();
        
        rider  = new User();
        rider.setFirstname("Daniela");
        rider.setLastname("Osorio");
        rider.setEmail("jedagos1409@gmail.com");
        rider.setStatus("init_ride");
        rider.setRole("rider");
        rider.setCreateAt(new Date());
        rider.setPhone("3014510118");
        
        driver  = new User();
        driver.setFirstname("Daniela");
        driver.setLastname("Osorio");
        driver.setEmail("jedagos1409@gmail.com");
        driver.setStatus("free");
        driver.setRole("driver");
        driver.setLastService(new Date());
        driver.setCreateAt(new Date());
        driver.setPhone("3014510118");
        
        users.add(driver);
        users.add(rider);
        
        ride = new Ride();
        ride.setStatus("init");
        ride.setCreateAt(new Date());
        ride.setUser(users);
        
    }
 
    @Test
    void saveRide(){
        when(rideDao.save(any(Ride.class))).thenReturn(ride);
        assertNotNull(rideImpl.saveRide(new Ride()));
    }
    
    @SuppressWarnings("unchecked")
    @Test
    void findAUserFree() {
        when(userImpl.getDriverFree()).thenReturn(Arrays.asList(driver));
        assertNotNull(rideImpl.findAUserFree());
    }
    
}
