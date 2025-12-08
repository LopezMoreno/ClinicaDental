package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Doctor;
import com.example.ClinicaDental.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    //Método para filtrar a todos los doctores en lista
    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    //Método para crear un doctor
    public Doctor save(Doctor doctor){
        return doctorRepository.save(doctor);
    }
    public Doctor update(Long id, Doctor doctor) {
        doctor.setId(id);
        return doctorRepository.save(doctor);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}

