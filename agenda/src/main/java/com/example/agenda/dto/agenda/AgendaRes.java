package com.example.agenda.dto.agenda;

import com.example.agenda.model.Agenda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRes {
    private Long id;
    public String date;
    public String activity;
    public String subject;

    public static AgendaRes fromEntity(Agenda agenda) {
        return new AgendaRes(
                agenda.getId(),
                agenda.getDate(),
                agenda.getActivity(),
                agenda.getSubject());

    }

    public static List<AgendaRes> fromEntities(List<Agenda> agendas) {
        List<AgendaRes> agendasRes = new ArrayList<>();
        for (Agenda agenda : agendas) {
            agendasRes.add(fromEntity(agenda));
        }
        return agendasRes;
    }
}