package com.swp.ArtQuack.view;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class TransactionOfLearner {
    private int transactionID;
    private Date date;
    private double money;

    private int to_instructorID;
    private String instructorName;

    private int courseID;
    private String courseName;
}
