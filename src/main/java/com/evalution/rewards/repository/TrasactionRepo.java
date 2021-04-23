package com.evalution.rewards.repository;

import com.evalution.rewards.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrasactionRepo extends JpaRepository<Transaction, Long> {

}
