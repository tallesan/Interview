package com.example.Interview.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ResponseUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users users;
    @ManyToOne
    private Question question;
}
