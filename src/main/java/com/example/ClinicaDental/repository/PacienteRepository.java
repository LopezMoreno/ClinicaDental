package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
}
