package com.example.agenda.dto.agenda;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdateAgendaReq implements Serializable {
    public Long id;
    public String date;
    public String activity;
    public String subject;
}
