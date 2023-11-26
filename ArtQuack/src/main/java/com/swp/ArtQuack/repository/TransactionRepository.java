package com.swp.ArtQuack.repository;

import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public List<Transaction> findByFromWalletLearner(Learner learner);

    public List<Transaction> findByToWalletInstructor(Instructor instructor);


}
