package com.banking.bankingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private String transactionType;
    private BigDecimal amount;
    private Long fromAccountId;
    private Long toAccountId;
    private LocalDateTime createdAt;
}
