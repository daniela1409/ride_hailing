package com.note_generator.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.note_generator.app.models.entity.User;

public interface UserDao extends CrudRepository<User, Integer>{

}
