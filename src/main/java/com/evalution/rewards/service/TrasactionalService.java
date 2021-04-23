package com.evalution.rewards.service;

import com.evalution.rewards.entity.Transaction;
import com.evalution.rewards.repository.TrasactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrasactionalService {
    @Autowired
    public TrasactionRepo trasactionRepo;

    public void save(Transaction transaction) {
        trasactionRepo.save(transaction);
    }


    public List<Transaction> findAll() {
        return trasactionRepo.findAll();
    }

}
