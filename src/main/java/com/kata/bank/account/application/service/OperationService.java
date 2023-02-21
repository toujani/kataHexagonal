package com.kata.bank.account.application.service;

import com.kata.bank.account.adapter.out.persistence.entity.Activity;
import com.kata.bank.account.adapter.out.persistence.entity.BankAccount;
import com.kata.bank.account.adapter.out.persistence.repository.BankAccountRepository;
import com.kata.bank.account.adapter.out.persistence.repository.OperationRepository;
import com.kata.bank.account.application.port.in.IOperationService;
import com.kata.bank.account.domain.ActivityDto;
import com.kata.bank.account.domain.OperationType;
import com.kata.bank.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@UseCase
@Transactional
public class OperationService implements IOperationService {


    private final OperationRepository operationRepository;


    private final BankAccountRepository bankAccountRepository;


    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ActivityDto deposit(Long id, Double amount) throws UnauthorizedOperationException {
        Activity activity = new Activity(OperationType.DEPOSIT, amount);

        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (bankAccount.isPresent()) {
            bankAccount.get().setBalance(bankAccount.get().getBalance() + amount);
            bankAccountRepository.save(bankAccount.get());
            activity.setBankAccount(bankAccount.get());
        } else {
            throw new UnauthorizedOperationException(id, OperationType.DEPOSIT);
        }
        activity = operationRepository.save(activity);

        return modelMapper.map(activity, ActivityDto.class);
    }

    @Override
    public ActivityDto withdraw(Long id, Double amount) throws UnauthorizedOperationException {
        amount = amount > 0 ? (-1 * amount) : amount;

        Activity activity = new Activity(OperationType.WITHDRAWAL, amount);

        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (bankAccount.isPresent()) {
            activity.setBankAccount(bankAccount.get());

            bankAccount.get().setBalance(bankAccount.get().getBalance() + amount);
            bankAccountRepository.save(bankAccount.get());
        } else {
            throw new UnauthorizedOperationException(id, OperationType.WITHDRAWAL);
        }
        return modelMapper.map(operationRepository.save(activity), ActivityDto.class);
    }

    @Override
    public List<ActivityDto> allOperations(Long id) {
        List<Activity> activities = operationRepository.findAll();
        return activities
                .stream()
                .map(activity -> modelMapper.map(activity, ActivityDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Double getBalance(Long id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        return bankAccount.orElseGet(BankAccount::new).getBalance();
    }


}
