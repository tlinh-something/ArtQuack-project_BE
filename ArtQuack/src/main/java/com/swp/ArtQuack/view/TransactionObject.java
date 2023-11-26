package com.swp.ArtQuack.view;

import com.swp.ArtQuack.entity.Learner;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class TransactionObject {
    private int transactionID;
    private Date date;
    private double money;



    private int from_learnerID;
    private String learnerName;

//    private int to_instructorID;
//    private String instructorName;
//
    private int courseID;
    private String courseName;
}
