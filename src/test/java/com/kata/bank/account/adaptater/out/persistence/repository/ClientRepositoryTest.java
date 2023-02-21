package com.kata.bank.account.adaptater.out.persistence.repository;

import com.kata.bank.account.adapter.out.persistence.entity.Client;
import com.kata.bank.account.adapter.out.persistence.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ClientRepositoryTest {

    private static Client client;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setLastName("Toujeni");
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        Assertions.assertEquals(this.clientRepository.findById(savedClient.getId()), client);
    }

    @Test
    public void saveClientAndFindByName() {
        Client savedClient = this.clientRepository.save(client);
        List<Client> clients = this.clientRepository.findByLastName(savedClient.getLastName());
        Assertions.assertEquals(clients.get(0).getLastName(), "Toujeni");
    }
}