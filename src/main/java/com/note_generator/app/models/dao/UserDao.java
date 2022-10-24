package com.note_generator.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.note_generator.app.models.entity.User;

public interface UserDao extends CrudRepository<User, Integer>{
	
	@Query("select u from User u")
	public List<User> findByRoleAnStatus(String term);

}
