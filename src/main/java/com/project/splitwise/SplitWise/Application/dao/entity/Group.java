package com.project.splitwise.SplitWise.Application.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(name = "group_id"),  // Foreign key in the join table referencing Group
            inverseJoinColumns = @JoinColumn(name = "user_id")  // Foreign key in the join table referencing User
    )
    List<User> users;

    @OneToMany(mappedBy = "group")
    private List<Transaction> transactions;

}