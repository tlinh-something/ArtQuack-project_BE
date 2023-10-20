//package com.example.demo.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private int artworkID;
//    private int userID;
//    private int instructorID;
//    private int rate;
//    private String comment;
//    private LocalDateTime dateTime;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="course_id")
//    private Course course;
//}
