package com.swp.ArtQuack.service;

import com.swp.ArtQuack.entity.*;
import com.swp.ArtQuack.repository.TransactionRepository;
import com.swp.ArtQuack.view.WithdrawalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Transaction> retrieveAllTransactions(){
        return transactionRepository.findAll();
    }

//    public WithdrawalRequest displayRender(Transaction x) {
//        WithdrawalRequest object = new WithdrawalRequest();
//        object.setTransactionID(x.getTransactionID());
//        object.setDate(x.getDate());
//        object.setMoney(x.getMoney());
//
//        object.setFrom_learnerID(x.getEnrollment().getLearner().getLearnerID());
//        object.setLearnerName(x.getEnrollment().getLearner().getName());
//
//        object.setTo_instructorID(x.getEnrollment().getCourse().getInstructor().getInstructorID());
//        object.setInstructorName(x.getEnrollment().getCourse().getInstructor().getName());
//
//        object.setCourseID(x.getEnrollment().getCourse().getCourseID());
//        object.setCourseName(x.getEnrollment().getCourse().getName());
//
//        return object;
//    }


}
