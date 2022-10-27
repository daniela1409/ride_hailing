package com.note_generator.app.models.services;

import com.note_generator.app.models.dto.PayMethodDTO;
import com.note_generator.app.models.entity.PayMethod;
import com.note_generator.app.models.entity.User;

public interface IPayMethods {
    public PayMethod getPayMethod(Integer payMethodId);
    public PayMethod savePayMethod(PayMethodDTO payMethodDTO, Integer idPayMethod, String status, User user);
    public PayMethod save(PayMethod payMethod);
}
