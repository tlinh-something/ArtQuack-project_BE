package com.swp.ArtQuack.controller;

import com.swp.ArtQuack.entity.Category;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Learner;
import com.swp.ArtQuack.entity.Transaction;
import com.swp.ArtQuack.service.LearnerService;
import com.swp.ArtQuack.service.TransactionService;
import com.swp.ArtQuack.view.CourseObject;
import com.swp.ArtQuack.view.TransactionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

}
