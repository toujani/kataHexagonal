package com.kata.bank.account.adapter.out.persistence.repository;

import com.kata.bank.account.adapter.out.persistence.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findById(int id);
}
