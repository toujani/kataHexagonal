package com.kata.bank.account.adapter.out.persistence.entity;

import com.kata.bank.account.domain.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount is mandatory")
    private Double amount;

    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

    @NotNull(message = "Operation type is mandatory")
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @NotNull(message = "Bank account is mandatory")
    @ManyToOne
    private BankAccount bankAccount;

    public Activity(OperationType type, Double amount) {
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return "Operation{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", date=" + date.format(formatter) + "}";
    }
}
