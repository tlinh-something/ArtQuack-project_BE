package com.swp.ArtQuack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Admin {
    @Id
    @Column(name = "adminID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int adminID;
    String username;
    String password;

    @OneToOne(mappedBy = "admin")
    @ToString.Exclude
    private Wallet wallet;
}
