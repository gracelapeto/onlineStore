package com.example.onlineStore.entities;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.LifecycleState;
import org.aspectj.weaver.ast.Literal;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "authors")

public class Author {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
@OneToMany
    private List< Product> products;
}
