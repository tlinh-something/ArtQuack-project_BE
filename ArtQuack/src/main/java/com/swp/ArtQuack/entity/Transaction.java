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

	@Column(name = "stk")
	private String stk;

	@Column(name = "Bank")
	private String bank;

	@Column(name = "amount")
	private double amount = 0.0;

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

	@ManyToOne(targetEntity = Enrollment.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "enrollmentID", referencedColumnName = "enrollmentID", nullable = true, insertable = true, updatable = false)
	@ToString.Exclude
	private Enrollment enrollment;

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Wallet getFromWallet() {
		return fromWallet;
	}

	public void setFromWallet(Wallet fromWallet) {
		this.fromWallet = fromWallet;
	}

	public Wallet getToWallet() {
		return toWallet;
	}

	public void setToWallet(Wallet toWallet) {
		this.toWallet = toWallet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
