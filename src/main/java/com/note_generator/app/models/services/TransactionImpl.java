package com.note_generator.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note_generator.app.models.dao.TransactionDao;
import com.note_generator.app.models.dto.TransactionDTO;
import com.note_generator.app.models.entity.PayMethod;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.entity.Transaction;

@Service
public class TransactionImpl implements ITransaction {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public Transaction saveTransaction(TransactionDTO transactionDTO, String transactionId, Ride ride,
            PayMethod payMethod) {

        Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setReference(transactionDTO.getReference());
        transaction.setAmountInCents(transactionDTO.getAmount_in_cents());
        transaction.setCurrency(transactionDTO.getCurrency());
        transaction.setInstallments(transactionDTO.getInstallments());
        transaction.setRide(ride);
        transaction.setPayMethod(payMethod);
        
        save(transaction);

        return transaction;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionDao.save(transaction);
    }
}
