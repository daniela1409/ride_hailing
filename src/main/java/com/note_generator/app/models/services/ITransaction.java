package com.note_generator.app.models.services;

import com.note_generator.app.models.dto.TransactionDTO;
import com.note_generator.app.models.entity.PayMethod;
import com.note_generator.app.models.entity.Ride;
import com.note_generator.app.models.entity.Transaction;

public interface ITransaction {

    public Transaction saveTransaction(TransactionDTO transactionDTO, String string, Ride ride, PayMethod payMethod);
    public Transaction save(Transaction transaction);
}
