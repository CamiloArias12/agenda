package com.example.agenda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Agenda {
    public Agenda() {
    }

    public Agenda(
            String date,
            String subject,
            String activity) {
        this.date = date;
        this.subject = subject;
        this.activity = activity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agenda_id")
    private Long id;

    @Column
    private String date;

    @Column
    private String subject;

    @Column
    private String activity;

}