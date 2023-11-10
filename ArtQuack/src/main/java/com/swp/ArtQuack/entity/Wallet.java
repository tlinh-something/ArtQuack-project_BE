package com.swp.ArtQuack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@EqualsAndHashCode
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walletID")
    private int walletID;

    @Column(name = "balance")
    private double balance;

    @OneToOne(targetEntity = Instructor.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructorID", referencedColumnName = "instructorID", nullable = true, insertable = true, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Instructor instructor;

    @OneToOne(targetEntity = Learner.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "learnerID", referencedColumnName = "learnerID", nullable = true, insertable = true, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Learner learner;

    @OneToOne(targetEntity = Admin.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "adminID", referencedColumnName = "adminID", nullable = true, insertable = true, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Admin admin;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "fromWallet")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Transaction> transactionsFrom;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "toWallet")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Transaction> transactionsTo;

	public int getWalletID() {
		return walletID;
	}

	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Learner getLearner() {
		return learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Collection<Transaction> getTransactionsFrom() {
		return transactionsFrom;
	}

	public void setTransactionsFrom(Collection<Transaction> transactionsFrom) {
		this.transactionsFrom = transactionsFrom;
	}

	public Collection<Transaction> getTransactionsTo() {
		return transactionsTo;
	}

	public void setTransactionsTo(Collection<Transaction> transactionsTo) {
		this.transactionsTo = transactionsTo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
