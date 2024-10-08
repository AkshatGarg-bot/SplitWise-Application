package com.project.splitwise.SplitWise.Application.dao.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private  String phn;

    @ManyToMany(mappedBy = "users")
    List<Group> groups;

}
