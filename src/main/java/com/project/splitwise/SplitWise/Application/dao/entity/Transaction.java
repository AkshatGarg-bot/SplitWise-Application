package com.project.splitwise.SplitWise.Application.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
public class Transaction {
    @Id
    private Long id = Math.abs(UUID.randomUUID().toString().hashCode() % 100L);
    private Long fromUserId;
    private Long toUserId;
    private Double amount;
    private Long groupId;
}
