package com.kata.bank.account.application.service;

import com.kata.bank.account.adapter.out.persistence.entity.BankAccount;
import com.kata.bank.account.domain.OperationType;

public class UnauthorizedOperationException extends Exception {

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "Unauthorized Transaction %s on this account %s";

    public UnauthorizedOperationException(Long id, OperationType transactionType) {
        super(String.format(MESSAGE, transactionType, id));
    }
}
