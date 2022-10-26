package com.note_generator.app.models.services;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.note_generator.app.models.dao.LocationDao;
import com.note_generator.app.models.entity.Location;

@ExtendWith(MockitoExtension.class)
public class LocationImplTest {
    
    @Mock
    private LocationDao locationDao;
    
    @InjectMocks
    private LocationImpl locationImpl;
    
    private Location location;
    @BeforeEach 
    void setUp()
    {
        location = new Location();
        location.setLongitude("12");
        location.setLatitude("14");
        
    }
    
    @Test
    void saveRide(){
        when(locationDao.save(any(Location.class))).thenReturn(location);
        assertNotNull(locationImpl.save(new Location()));
    }

}
