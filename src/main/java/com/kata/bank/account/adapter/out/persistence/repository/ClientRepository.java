package com.kata.bank.account.adapter.out.persistence.repository;

import com.kata.bank.account.adapter.out.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findById(int id);
    List<Client> findByLastName(String name);
}
