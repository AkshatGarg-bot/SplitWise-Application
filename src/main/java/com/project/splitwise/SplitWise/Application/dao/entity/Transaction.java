package com.project.splitwise.SplitWise.Application.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromUser;

    private Long toUser;

    private Double amount;


    private Long groupId;

}
