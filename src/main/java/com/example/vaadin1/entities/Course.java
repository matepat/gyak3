package com.example.vaadin1.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private Room room;
}
