package com.note_generator.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note_generator.app.models.dao.PayMethodDao;
import com.note_generator.app.models.dto.PayMethodDTO;
import com.note_generator.app.models.entity.PayMethod;
import com.note_generator.app.models.entity.User;

@Service
public class PayMethodsImpl implements IPayMethods{
    @Autowired
    private PayMethodDao payMethodDao;
    
    @Autowired
    private IUser iUser;
    
    @Override
    public PayMethod getPayMethod(Integer payMethodId) {
        
        return payMethodDao.findById(payMethodId).orElse(null);
    
    }

    @Override
    public PayMethod savePayMethod(PayMethodDTO payMethodDTO, Integer idPayMethod, String status, User user) {
        
        PayMethod payMethod = new PayMethod();
        payMethod.setCustomerEmail(payMethodDTO.getCustomer_email());
        payMethod.setToken(payMethodDTO.getToken());
        payMethod.setType(payMethodDTO.getType());
        payMethod.setId(idPayMethod);
        payMethod.setStatus(status);
        save(payMethod);
        
        user.setPayMethod(payMethod);
        iUser.save(user);
        
        return payMethod;
    }

    @Override
    public PayMethod save(PayMethod payMethod) {
        return payMethodDao.save(payMethod);
    }
}
