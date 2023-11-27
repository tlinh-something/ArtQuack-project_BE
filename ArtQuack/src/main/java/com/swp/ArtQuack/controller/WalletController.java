package com.swp.ArtQuack.controller;

import com.swp.ArtQuack.entity.Instructor;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.entity.Transaction;
import com.swp.ArtQuack.entity.Wallet;
import com.swp.ArtQuack.repository.TransactionRepository;
import com.swp.ArtQuack.repository.WalletRepository;
import com.swp.ArtQuack.service.LearnerService;
import com.swp.ArtQuack.service.WalletService;
import com.swp.ArtQuack.view.WalletInstructor;
import com.swp.ArtQuack.view.WalletLearner;
import com.swp.ArtQuack.view.WithdrawDTO;
import com.swp.ArtQuack.view.WithdrawalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WalletController {

    private final WalletService walletService;

    private LearnerService learnerService;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;


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


    @PostMapping ("/{instructorID}/withdraw")
    public ResponseEntity<String> withdrawMoney(
            @PathVariable int instructorID,
            @RequestParam String stk,
            @RequestParam String bank,
            @RequestParam double amount
    ) {
        // Retrieve the instructor's wallet
        Optional<Wallet> walletOptional = Optional.ofNullable(walletRepository.findWalletByInstructorInstructorID(instructorID));
        if (walletOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
        }

        Wallet wallet = walletOptional.get();
        double currentBalance = wallet.getBalance();

        // Check if the wallet has sufficient balance
        if (currentBalance < amount) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
        }

        // Update the wallet balance
        double newBalance = currentBalance - amount;
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);

        // Create a new transaction record
        Transaction transaction = new Transaction();
        transaction.setStk(stk);
        transaction.setBank(bank);
        transaction.setAmount(amount);
        transaction.setMoney(-amount); // Negative amount represents a withdrawal
        transaction.setFromWallet(wallet);
        transaction.setDate(new Date()); // Set the current date
        transactionRepository.save(transaction);

        return ResponseEntity.ok("Withdrawal successful");
    }

}
