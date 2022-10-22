package com.note_generator.app.models.services;

import com.note_generator.app.models.entity.PayMethod;

public interface IPayMethod {

	public Boolean generatePayMethod();
	public PayMethod getPayMethod(Integer payMethodId);
}
