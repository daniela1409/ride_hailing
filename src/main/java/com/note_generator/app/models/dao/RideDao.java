package com.note_generator.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.note_generator.app.models.entity.Ride;

public interface RideDao extends CrudRepository<Ride, Integer>{

}
