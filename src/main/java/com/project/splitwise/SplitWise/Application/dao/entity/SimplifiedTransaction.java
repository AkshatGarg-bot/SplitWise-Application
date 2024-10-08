package com.project.splitwise.SplitWise.Application.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "simplifiedTransactions")
@Getter
@Setter
public class SimplifiedTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User from;
    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User to;
    private long Amount;
}

