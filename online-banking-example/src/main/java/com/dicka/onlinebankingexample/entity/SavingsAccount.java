package com.dicka.onlinebankingexample.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "savings_account")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SavingsAccount implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "savingsAccount", cascade =
    CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SavingsTransaction> savingsTransactionList;

}
