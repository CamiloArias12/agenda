package com.example.agenda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.dto.agenda.AgendaRes;
import com.example.agenda.dto.agenda.CreateAgendaReq;
import com.example.agenda.dto.agenda.DeleteAgendaReq;
import com.example.agenda.dto.agenda.UpdateAgendaReq;
import com.example.agenda.model.Agenda;
import com.example.agenda.service.AgendaService;

@RestController
public class AgendaController {
    @Autowired
    private AgendaService service;

    @GetMapping("api/agenda/{id}")
    public ResponseEntity<AgendaRes> getById(@PathVariable Long id) {
        Agenda agenda = service.findById(id);
        if (agenda == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(AgendaRes.fromEntity(agenda));
    }

    @GetMapping("api/agenda")
    public ResponseEntity<List<AgendaRes>> findAll() {
        List<Agenda> agenda = service.findAll();
        if (agenda == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(AgendaRes.fromEntities(agenda));
    }

    @PostMapping(value = "api/agenda",consumes = "application/json")
    public ResponseEntity<AgendaRes> create(@RequestBody CreateAgendaReq agendaReq) {
        Agenda agenda = new Agenda(agendaReq.getDate(), agendaReq.getSubject(), agendaReq.getActivity());
        agenda = service.create(agenda);

        return ResponseEntity.status(HttpStatus.CREATED).body(AgendaRes.fromEntity(agenda));
    }

    @PutMapping(value = "api/agenda",consumes = "application/json")
    public ResponseEntity<AgendaRes> update(@RequestBody UpdateAgendaReq agendaReq) {

        Agenda agendaFound = service.findById(agendaReq.getId());
        if (agendaFound == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        agendaFound.setDate(agendaReq.getDate());
        agendaFound.setActivity(agendaReq.getActivity());
        agendaFound.setSubject(agendaReq.getSubject());

        service.update(agendaFound);

        return ResponseEntity.status(HttpStatus.OK).body(AgendaRes.fromEntity(agendaFound));
    }

    @DeleteMapping("api/agenda/{id}")
    public ResponseEntity<AgendaRes> delete(
        @PathVariable Long id) {
        Agenda agenda = new Agenda();
        agenda = service.findById(id);
        if (agenda == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        agenda.setId(id);
        service.delete(agenda);
        return ResponseEntity.status(HttpStatus.OK).body(AgendaRes.fromEntity(agenda));
    }
}