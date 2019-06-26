package com.example.vaadin1.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int level;
}
