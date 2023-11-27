package com.swp.ArtQuack.repository;

import com.swp.ArtQuack.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    public Wallet findWalletByLearnerLearnerID(int learnerID);
    public Wallet findWalletByInstructorInstructorID(int instructorID);
    public Wallet findWalletByAdminAdminID(int adminID);

}
