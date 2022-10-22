package com.note_generator.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.note_generator.app.models.entity.Transaction;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer>{

}
