package com.dicka.onlinebankingexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "primary_account")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryAccount implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "primaryAccount",
    cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PrimaryTransaction> primaryTransactionList;


}
