package com.swp.ArtQuack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swp.ArtQuack.entity.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String>{

}
