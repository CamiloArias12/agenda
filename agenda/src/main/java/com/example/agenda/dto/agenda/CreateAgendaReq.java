package com.example.agenda.dto.agenda;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateAgendaReq implements Serializable {
    public String date;
    public String activity;
    public String subject;
}
