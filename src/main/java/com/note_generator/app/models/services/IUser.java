package com.note_generator.app.models.services;

import java.util.List;

import com.note_generator.app.models.entity.User;

public interface IUser {
    
    public User getUser(Integer userId);

    public List<User> getDriverFree();

    public User save(User user);
}
