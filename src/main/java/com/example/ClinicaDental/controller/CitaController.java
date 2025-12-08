package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Cita;
import com.example.ClinicaDental.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")


public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Long id) {
        return citaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCita(@RequestBody Cita cita) {
        if (cita.getFecha() == null || cita.getFecha().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("La fecha de la cita es obligatoria y debe ser actual o futura");
        }
        if (cita.getMotivo() == null || cita.getMotivo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'motivo' es obligatorio");
        }
        if (cita.getTratamiento() == null) {
            return ResponseEntity.badRequest().body("Debe asociar un 'tratamiento' a la cita");
        }
        if (cita.getEstado() == null || cita.getEstado().trim().isEmpty()) {
            cita.setEstado("pendiente");
        }

        return ResponseEntity.ok(citaService.save(cita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable Long id, @RequestBody Cita cita) {
        if (citaService.findById(id).isPresent()) {
            return ResponseEntity.ok(citaService.update(id, cita));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        if (citaService.findById(id).isPresent()) {
            citaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
