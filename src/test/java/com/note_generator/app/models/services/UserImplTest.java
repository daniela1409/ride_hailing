package com.note_generator.app.models.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.note_generator.app.models.dao.UserDao;
import com.note_generator.app.models.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserImplTest {
    
    @Mock 
    private UserDao userDao;
    
    @InjectMocks
    private UserImpl userImpl;
    
    private User user;
    
    @BeforeEach 
    void setUp()
    {
        user  = new User();
        user.setFirstname("Daniela");
        user.setLastname("Osorio");
        user.setEmail("jedagos1409@gmail.com");
        user.setStatus("free");
        user.setRole("driver");
        user.setLastService(new Date());
        user.setCreateAt(new Date());
        user.setPhone("3014510118");
        
    }
 
    @Test 
    void saveUser()
    {
        when(userDao.save(any(User.class))).thenReturn(user);
        assertNotNull(userImpl.save(new User()));
    }
    
    @Test
    void getDriverFree()
    {
        when(userDao.findFreeDriver()).thenReturn(Arrays.asList(user));
        assertNotNull(userImpl.getDriverFree());
    }
}
