package com.note_generator.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note_generator.app.models.dao.UserDao;
import com.note_generator.app.models.entity.User;

@Service
public class UserImpl implements IUser{

	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(Integer userId) {
		return userDao.findById(userId).orElse(null);
	}
}
