package com.swp.ArtQuack.repository;

import com.swp.ArtQuack.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public List<Transaction> findByFromWalletLearner(Learner learner);

    public List<Transaction> findByToWalletInstructor(Instructor instructor);



}
