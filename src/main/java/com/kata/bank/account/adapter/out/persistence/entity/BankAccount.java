package com.kata.bank.account.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "bank_account")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Builder.Default
    private Double balance = 0.0;

    private LocalDate creationDate;

    @NotNull(message = "Client is mandatory")
    @OneToOne
    private Client client;

}
