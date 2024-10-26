package com.example.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agenda.model.Agenda;
import com.example.agenda.repository.AgendaRepository;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository repository;

    public Agenda create(Agenda agenda) {
        return repository.save(agenda);
    }

    public Agenda findById(Long id) {
        Optional<Agenda> agenda = repository.findById(id);
        if (agenda.isEmpty())
            return null;

        return agenda.get();
    }

    public void update(Agenda agenda) {
        repository.save(agenda);
    }

    public void delete(Agenda agenda) {
        repository.delete(agenda);
    }

    public List<Agenda> findAll() {
        return repository.findAll();
    }

}
