package com.swp.ArtQuack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "transactions")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "transactionID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    @Column(name = "date")
    private Date date;

    @Column(name = "money")
    private double money;

    @ManyToOne(targetEntity = Wallet.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_walletID", referencedColumnName = "walletID", nullable = true, insertable = true, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Wallet fromWallet;

    @ManyToOne(targetEntity = Wallet.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_walletID", referencedColumnName = "walletID", nullable = true, insertable = true, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Wallet toWallet;

}
