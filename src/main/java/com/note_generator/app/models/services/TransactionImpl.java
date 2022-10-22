package com.note_generator.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note_generator.app.models.dao.TransactionDao;

@Service
public class TransactionImpl implements ITransaction{
	
	@Autowired
	private TransactionDao transactionDao;
}
