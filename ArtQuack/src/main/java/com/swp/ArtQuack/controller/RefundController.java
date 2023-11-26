package com.swp.ArtQuack.controller;

import com.swp.ArtQuack.entity.Transaction;
import com.swp.ArtQuack.entity.Wallet;
import com.swp.ArtQuack.repository.TransactionRepository;
import com.swp.ArtQuack.repository.WalletRepository;
import com.swp.ArtQuack.view.RefundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RefundController {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/instructor/{instructorID}/student/{studentID}/refund-money-for-learner")
    public ResponseEntity<String> processRefund(@PathVariable int instructorID,
                                                @PathVariable int studentID,
                                                @RequestBody RefundRequest refundRequest) {
        // Retrieve instructor's wallet
        Optional<Wallet> optionalInstructorWallet = Optional.ofNullable(walletRepository.findWalletByInstructorInstructorID(instructorID));
        if (optionalInstructorWallet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Wallet instructorWallet = optionalInstructorWallet.get();

        // Retrieve student's wallet
        Optional<Wallet> optionalStudentWallet = Optional.ofNullable(walletRepository.findWalletByLearnerLearnerID(studentID));
        if (optionalStudentWallet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Wallet studentWallet = optionalStudentWallet.get();

        // Check if instructor's wallet has sufficient balance for the refund
        double refundAmount = refundRequest.getRefundAmount();
        if (instructorWallet.getBalance() < refundAmount) {
            return ResponseEntity.badRequest().body("Insufficient balance in the instructor's wallet.");
        }

        // Process the refund
        instructorWallet.setBalance(instructorWallet.getBalance() - refundAmount);
        studentWallet.setBalance(studentWallet.getBalance() + refundAmount);

        // Save the updated wallets
        walletRepository.save(instructorWallet);
        walletRepository.save(studentWallet);

        // Create transaction records
        Transaction instructorTransaction = new Transaction();
        instructorTransaction.setDate(new Date());
        instructorTransaction.setMoney(-refundAmount);
        instructorTransaction.setFromWallet(instructorWallet);
        instructorTransaction.setToWallet(studentWallet);
        transactionRepository.save(instructorTransaction);

        Transaction studentTransaction = new Transaction();
        studentTransaction.setDate(new Date());
        studentTransaction.setMoney(refundAmount);
        studentTransaction.setFromWallet(instructorWallet);
        studentTransaction.setToWallet(studentWallet);
        transactionRepository.save(studentTransaction);

        return ResponseEntity.ok("Refund processed successfully.");
    }


}
