package com.swp.ArtQuack.service;

import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.entity.Wallet;
import com.swp.ArtQuack.repository.WalletRepository;
import com.swp.ArtQuack.view.CourseObject;
import com.swp.ArtQuack.view.WalletInstructor;
import com.swp.ArtQuack.view.WalletLearner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet findWalletOfLearner(int learnerID){
        return walletRepository.findWalletByLearnerLearnerID(learnerID);
    }

    public Wallet findWalletOfInstructor(int instructorID){
        return walletRepository.findWalletByInstructorInstructorID(instructorID);
    }

    public Wallet createLearnerWallet(Learner learner) {
        Wallet wallet = new Wallet();
        wallet.setLearner(learner);

        return walletRepository.save(wallet);
    }

    public Wallet createInstructorWallet(Instructor instructor) {
        Wallet wallet = new Wallet();
        wallet.setInstructor(instructor);

        return walletRepository.save(wallet);
    }

    public WalletLearner displayRender(Wallet x) {
        WalletLearner object = new WalletLearner();
        object.setWalletID(x.getWalletID());
        object.setBalance(x.getBalance());

        object.setLearnerID(x.getLearner().getLearnerID());
        object.setLearnerName(x.getLearner().getName());

        return object;
    }

    public WalletInstructor display(Wallet x) {
        WalletInstructor object = new WalletInstructor();
        object.setWalletID(x.getWalletID());
        object.setBalance(x.getBalance());

        object.setInstrucrtorID(x.getInstructor().getInstructorID());
        object.setInstructorName(x.getInstructor().getName());

        return object;
    }
}
