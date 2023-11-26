package com.swp.ArtQuack.service;

import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.entity.Transaction;
import com.swp.ArtQuack.entity.Wallet;
import com.swp.ArtQuack.repository.TransactionRepository;
import com.swp.ArtQuack.view.TransactionObject;
import com.swp.ArtQuack.view.WalletLearner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;



    public List<Transaction> getLearnerTransactions(int learnerId) {
        Learner learner = new Learner();
        learner.setLearnerID(learnerId);
        return transactionRepository.findByFromWalletLearner(learner);
    }

    public List<Transaction> getInstructorTransactions(int instructorID){
        Instructor instructor = new Instructor();
        instructor.setInstructorID(instructorID);
        return transactionRepository.findByToWalletInstructor(instructor);
    }
}
