package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Cita;
import com.example.ClinicaDental.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    //Método para filtrar a todos los tratamientos en lista
    public List<Cita> findAll(){
        return citaRepository.findAll();
    }

    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    //Método para crear un tratamiento
    public Cita save(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita update(Long id, Cita cita) {
        cita.setId(id);
        return citaRepository.save(cita);
    }

    public void deleteById(Long id) { citaRepository.deleteById(id);
    }

}
