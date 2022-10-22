package com.note_generator.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.note_generator.app.models.entity.Location;

@Repository
public interface LocationDao extends CrudRepository<Location, Integer>{

}
