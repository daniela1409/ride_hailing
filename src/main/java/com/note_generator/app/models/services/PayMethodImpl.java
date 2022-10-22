package com.note_generator.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note_generator.app.models.dao.PayMethodDao;
import com.note_generator.app.models.entity.PayMethod;

@Service
public class PayMethodImpl implements IPayMethod {

	@Autowired
	private PayMethodDao payMethodDao;

	@Override
	public Boolean generatePayMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayMethod getPayMethod(Integer payMethodId) {
		
		return payMethodDao.findById(payMethodId).orElse(null);
	
	}
	
	
}
