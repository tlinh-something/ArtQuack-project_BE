package com.swp.ArtQuack.view;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class WalletInstructor {
    private int walletID;
    private double balance;

    private int instrucrtorID;
    private String instructorName;
}
