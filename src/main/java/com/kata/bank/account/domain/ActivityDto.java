package com.kata.bank.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityDto {
    private Long id;

    private OperationType type;

    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @JsonProperty("balance")
    private Double bankAccountBalance;

    @JsonProperty("client")
    private String bankAccountClientLastName;

    public void setBankAccountClientLastName(String bankAccountClientLastName) {
        this.bankAccountClientLastName = bankAccountClientLastName;
    }
}
