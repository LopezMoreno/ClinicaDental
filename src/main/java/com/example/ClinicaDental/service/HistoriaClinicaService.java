package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.HistoriaClinica;
import com.example.ClinicaDental.repository.HistoriaClinicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;

    public HistoriaClinicaService(HistoriaClinicaRepository historiaClinicaRepository) {
        this.historiaClinicaRepository = historiaClinicaRepository;
    }

    //Método para filtrar a todas las historias clinicas en lista
    public List<HistoriaClinica> findAll(){
        return historiaClinicaRepository.findAll();
    }

    public Optional<HistoriaClinica> findById(Long id) {
        return historiaClinicaRepository.findById(id);
    }

    //Método para crear una historia clinica
    public HistoriaClinica save(HistoriaClinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }

    public HistoriaClinica update(Long id, HistoriaClinica historiaClinica) {
        historiaClinica.setId(id);
        return historiaClinicaRepository.save(historiaClinica);
    }

    public void deleteById(Long id) {
        historiaClinicaRepository.deleteById(id);
    }

}
