package com.swp.ArtQuack.repository;

import com.swp.ArtQuack.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findFirstByAdminIDNotNull();

    public Admin findByAdminID(int adminId);

    public Admin findByUsernameAndPassword(String username, String password);

}
