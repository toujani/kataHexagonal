package com.kata.bank.account.application.port.in;

import com.kata.bank.account.application.service.UnauthorizedOperationException;
import com.kata.bank.account.domain.ActivityDto;

import java.util.List;


public interface IOperationService {

    ActivityDto deposit(Long id, Double amount) throws UnauthorizedOperationException;

    ActivityDto withdraw(Long id, Double amount) throws UnauthorizedOperationException;

    List<ActivityDto> allOperations(Long id);

    Double getBalance(Long id);
}
