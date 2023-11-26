package com.swp.ArtQuack.view;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class WalletLearner {

    private int walletID;
    private double balance;

    private int learnerID;
    private String learnerName;
}
