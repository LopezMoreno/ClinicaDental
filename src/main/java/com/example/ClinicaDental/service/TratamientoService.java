package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Tratamiento;
import com.example.ClinicaDental.repository.TratamientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;

    public TratamientoService(TratamientoRepository tratamientoRepository) {
        this.tratamientoRepository = tratamientoRepository;
    }

    //Método para filtrar a todos los tratamientos en lista
    public List<Tratamiento> findAll(){
        return tratamientoRepository.findAll();
    }

    public Optional<Tratamiento> findById(Long id) {
        return tratamientoRepository.findById(id);
    }

    //Método para crear un tratamiento
    public Tratamiento save(Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
    }

    public Tratamiento update(Long id, Tratamiento tratamiento) {
        tratamiento.setId(id);
        return tratamientoRepository.save(tratamiento);
    }

    public void deleteById(Long id) { tratamientoRepository.deleteById(id);
    }

}
