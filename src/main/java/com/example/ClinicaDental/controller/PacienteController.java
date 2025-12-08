package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Paciente;
import com.example.ClinicaDental.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")

public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPaciente(@RequestBody Paciente paciente) {
        if (paciente.getNombre() == null || paciente.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'nombre' es obligatorio");
        }
        if (paciente.getApellido() == null || paciente.getApellido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'apellido' es obligatorio");
        }
        if (paciente.getDni() == null) {
            return ResponseEntity.badRequest().body("El campo 'dni' es obligatorio");
        }
        if (paciente.getHistorialMedico() == null || paciente.getHistorialMedico().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'HistorialMedico' es obligatorio");
        }
        if (paciente.getAlergias() == null || paciente.getAlergias().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'alergias' es obligatorio");
        }
        return ResponseEntity.ok(pacienteService.save(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        if (pacienteService.findById(id).isPresent()) {
            return ResponseEntity.ok(pacienteService.update(id, paciente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        if (pacienteService.findById(id).isPresent()) {
            pacienteService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
