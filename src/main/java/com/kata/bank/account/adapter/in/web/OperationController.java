package com.kata.bank.account.adapter.in.web;

import com.kata.bank.account.application.port.in.IOperationService;
import com.kata.bank.account.application.service.UnauthorizedOperationException;
import com.kata.bank.account.domain.ActivityDto;
import com.kata.bank.account.domain.ActivityRequestBody;
import com.kata.bank.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class OperationController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(OperationController.class));

    private final IOperationService operationService;


    @GetMapping(value = "/operations/{sourceAccountId}")
    public List<ActivityDto> getAllOperations(@PathVariable("sourceAccountId") Long sourceAccountId) {

        return operationService.allOperations(sourceAccountId);
    }

    @GetMapping(value = "/balance/{sourceAccountId}")
    public Double getBalance(@PathVariable("sourceAccountId") Long sourceAccountId) {
        return operationService.getBalance(sourceAccountId);
    }

    @PostMapping(value = "/deposit/{sourceAccountId}")
    public ResponseEntity<ActivityDto> deposit(
            @PathVariable("sourceAccountId") Long sourceAccountId,
            @RequestBody ActivityRequestBody operation) throws UnauthorizedOperationException {
        ActivityDto newOperation = operationService.deposit(sourceAccountId, operation.getAmount());

        if (newOperation == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }

    @PostMapping(value = "/withdraw/{sourceAccountId}")
    public ResponseEntity<ActivityDto> withdraw(
            @PathVariable("sourceAccountId") Long sourceAccountId,
            @RequestBody ActivityRequestBody operation) throws UnauthorizedOperationException {
        ActivityDto newOperation = operationService.withdraw(sourceAccountId, operation.getAmount());

        if (newOperation == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }

}