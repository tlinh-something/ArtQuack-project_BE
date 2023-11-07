package com.swp.ArtQuack.repository;

import com.swp.ArtQuack.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    public Wallet findWalletByLearnerLearnerID(int learnerID);
    public Wallet findWalletByInstructorInstructorID(int learnerID);
    public Wallet findWalletByAdminAdminID(int learnerID);
}
