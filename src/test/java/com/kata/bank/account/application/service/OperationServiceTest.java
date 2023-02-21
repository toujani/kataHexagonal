package com.kata.bank.account.application.service;

import com.kata.bank.account.adapter.out.persistence.entity.Activity;
import com.kata.bank.account.adapter.out.persistence.entity.BankAccount;
import com.kata.bank.account.adapter.out.persistence.entity.Client;
import com.kata.bank.account.adapter.out.persistence.repository.BankAccountRepository;
import com.kata.bank.account.adapter.out.persistence.repository.OperationRepository;
import com.kata.bank.account.domain.ActivityDto;
import com.kata.bank.account.domain.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class OperationServiceTest {



    @Mock
    OperationRepository operationRepository;

    @Mock
    BankAccountRepository bankAccountRepository;

    @Mock
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    OperationService operationService;


    @BeforeEach
    void setUp() {



    }

    @Test
    @Order(1)
    void deposit() throws UnauthorizedOperationException {
        Client client = new Client();
        client.setId(1L);
        client.setLastName("Ghazi");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setBalance(1000d);
        bankAccount.setClient(client);
        Activity activity = new Activity();
        activity.setAmount(100d);
        activity.setId(46L);
        activity.setType(OperationType.DEPOSIT);
        activity.setBankAccount(bankAccount);

        when(bankAccountRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(bankAccount));

        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenReturn(bankAccount);

        when(operationRepository.save(any(Activity.class)))
                .thenReturn(activity);
        ActivityDto deposit = operationService.deposit(1L, 100d);
        assertNotNull(deposit);
        assertEquals(deposit.getBankAccountBalance().toString(), "1000.0");
    }

    @Test
    @Order(2)
    void withdraw() throws UnauthorizedOperationException {
        Client client = new Client();
        client.setId(1L);
        client.setLastName("Ghazi");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);
        bankAccount.setBalance(1000d);
        bankAccount.setClient(client);
        Activity activity = new Activity();
        activity.setAmount(100d);
        activity.setId(46L);
        activity.setType(OperationType.DEPOSIT);
        activity.setBankAccount(bankAccount);

        when(bankAccountRepository.findById(1L))
                .thenReturn(Optional.ofNullable(bankAccount));

        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenReturn(bankAccount);

        when(operationRepository.save(any(Activity.class)))
                .thenReturn(activity);
        ActivityDto withdraw = operationService.withdraw(1L, 100d);
        assertNotNull(withdraw);
        assertEquals(withdraw.getBankAccountBalance().toString(), "900.0");
    }
}