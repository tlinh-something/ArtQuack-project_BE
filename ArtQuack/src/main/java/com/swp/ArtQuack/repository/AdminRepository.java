package com.swp.ArtQuack.repository;

import com.swp.ArtQuack.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findFirstByAdminIDNotNull();

    public Admin findByUsernameAndPassword(String username, String password);

}
