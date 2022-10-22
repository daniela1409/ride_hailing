package com.note_generator.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.note_generator.app.models.entity.PayMethod;

public interface PayMethodDao extends CrudRepository<PayMethod, Integer>{

}
