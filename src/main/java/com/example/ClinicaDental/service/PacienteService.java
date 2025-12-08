package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Paciente;
import com.example.ClinicaDental.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    //Método para filtrar a todos los pacientes en lista
    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    //Método para crear un paciente
    public Paciente save(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Paciente update(Long id, Paciente paciente) {
        paciente.setId(id);
        return pacienteRepository.save(paciente);
    }

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }
}
