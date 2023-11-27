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
public class WithdrawalRequest {
    private int stk;
    private String bank;
    private double amount;
}
