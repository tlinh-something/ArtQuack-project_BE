package com.swp.ArtQuack.controller;

import com.swp.ArtQuack.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SubmitController {

    @Autowired
    SubmitService submitService;
    @GetMapping("/submit/{instructorID}")
    public ResponseEntity getSubmitByInstructor(@PathVariable int instructorID){
        return ResponseEntity.ok(submitService.getSubmitList(instructorID));
    }
    
    @GetMapping("/submit-of-learner/{learnerID}")
    public ResponseEntity getSubmitByLearner(@PathVariable int learnerID){
        return ResponseEntity.ok(submitService.getSubmitListOfLearner(learnerID));
    }
}
