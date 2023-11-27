package com.swp.ArtQuack.view;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class WithdrawDTO {
    private int instructorID;
    private String name;
    private int stk;
    private String bank;

    private double amount;
}
