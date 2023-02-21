package com.kata.bank.account.adaptater.out.persistence.repository;

import com.kata.bank.account.adapter.out.persistence.entity.BankAccount;
import com.kata.bank.account.adapter.out.persistence.entity.Client;
import com.kata.bank.account.adapter.out.persistence.repository.BankAccountRepository;
import com.kata.bank.account.adapter.out.persistence.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BankAccountRepositoryTest {

    private static Client client;
    private static BankAccount bankAccount;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setLastName("Jamai");

        bankAccount = new BankAccount();
        bankAccount.setBalance(900.0);
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.bankAccountRepository.save(bankAccount);
        Assertions.assertEquals(savedBankAccount.getBalance(), 900.0);
    }
}