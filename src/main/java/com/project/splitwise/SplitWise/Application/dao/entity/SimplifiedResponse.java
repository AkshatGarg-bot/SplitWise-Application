package com.project.splitwise.SplitWise.Application.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Table(name="Display")
@Getter
@Setter
public class SimplifiedResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Group group;

    Map<User , Map<User , Double>> map;
}
