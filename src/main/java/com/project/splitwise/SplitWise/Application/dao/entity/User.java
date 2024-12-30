package com.project.splitwise.SplitWise.Application.dao.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = Math.abs(UUID.randomUUID().toString().hashCode() % 1_000_000_000L);
    private String name;

    private  String phn;

    List<Long> groupsIds;

}
