package com.kata.bank.account.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	@NotBlank(message = "Client name is mandatory")
	private String lastName;

}
