package com.kata.bank.account.adaptater.in.web;

import com.kata.bank.account.adapter.in.web.OperationController;
import com.kata.bank.account.application.service.OperationService;
import com.kata.bank.account.domain.ActivityDto;
import com.kata.bank.account.domain.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OperationController.class)
class OperationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OperationService operationService;

    @BeforeEach
    void setUp() {


        try {
            ActivityDto deposit = new ActivityDto();
            deposit.setId(1L);
            deposit.setBankAccountClientLastName("Dupont");
            deposit.setBankAccountBalance(100d);
            deposit.setType(OperationType.DEPOSIT);
            deposit.setAmount(500d);
            deposit.setDate(LocalDateTime.now());
            when(operationService.deposit(1l, 500d))
                    .thenReturn(deposit);

            ActivityDto withdraw = new ActivityDto();
            withdraw.setBankAccountClientLastName("Dupont");
            withdraw.setBankAccountBalance(0d);
            withdraw.setType(OperationType.WITHDRAWAL);
            withdraw.setAmount(100d);
            withdraw.setDate(LocalDateTime.now());
            when(operationService.withdraw(1l, 100d))
                    .thenReturn(withdraw);
        } catch (Exception ex) {

        }
    }

    @Test
    @Order(1)
    public void allOperations() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/operations/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void deposit() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/deposit/1")
                        .content("{\"amount\":100}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(100));
    }

    @Test
    @Order(3)
    public void withdraw() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/withdraw/1")
                        .content("{\"amount\":100}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0));
    }

}