package com.swp.ArtQuack.repository;

import com.swp.ArtQuack.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public  Transaction findByTransactionID(int transactionID);
}
