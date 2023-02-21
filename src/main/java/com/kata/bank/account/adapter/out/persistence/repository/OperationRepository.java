package com.kata.bank.account.adapter.out.persistence.repository;

import com.kata.bank.account.adapter.out.persistence.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Activity, Long> {
}
