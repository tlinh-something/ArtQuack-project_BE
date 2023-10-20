package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    private String course_code;
    private String instructorID;

    private String cateID;

    private String levelID;

    private String reviewID;

    @Id
    private String courseID;
    private String name;
    private String description;
    private LocalDateTime upload_date;

    private float price;

    private int viewer;

    private int buyer;

    private int rate;


}
