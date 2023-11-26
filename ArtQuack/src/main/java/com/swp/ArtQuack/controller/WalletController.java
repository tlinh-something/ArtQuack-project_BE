package com.swp.ArtQuack.controller;

import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.entity.Wallet;
import com.swp.ArtQuack.repository.TransactionRepository;
import com.swp.ArtQuack.service.LearnerService;
import com.swp.ArtQuack.service.WalletService;
import com.swp.ArtQuack.view.WalletInstructor;
import com.swp.ArtQuack.view.WalletLearner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WalletController {

    private final WalletService walletService;

    private LearnerService learnerService;


    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallet-of-learner/{learnerID}")
    public ResponseEntity<WalletLearner> findWalletOfLearner(@PathVariable("learnerID") int learnerID){
        Wallet wallet = walletService.findWalletOfLearner(learnerID);
        if (wallet != null) {
            return ResponseEntity.status(HttpStatus.OK).body(walletService.displayRender(wallet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/wallet-of-instructor/{instructorID}")
    public ResponseEntity<WalletInstructor> findWalletOfInstructor(@PathVariable("instructorID") int instructorID){
        Wallet wallet = walletService.findWalletOfInstructor(instructorID);
        if (wallet != null) {
            return ResponseEntity.status(HttpStatus.OK).body(walletService.display(wallet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
