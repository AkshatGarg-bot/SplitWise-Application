package com.project.splitwise.SplitWise.Application.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groupResponse")
@Getter
@Setter

public class GroupResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Group group;

    @ManyToOne
    List<SimplifiedTransaction> simplifiedTransactionList;
}
