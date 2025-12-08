package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.HistoriaClinica;
import com.example.ClinicaDental.service.HistoriaClinicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/api/historias_clinicas")


public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    public HistoriaClinicaController(HistoriaClinicaService historiaClinicaService) {
        this.historiaClinicaService = historiaClinicaService;
    }

    @GetMapping
    public List<HistoriaClinica> getAllHistoriasClinicas() {
        return historiaClinicaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> getHistoriaClinicaById(@PathVariable Long id) {
        return historiaClinicaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva historia clínica
    @PostMapping
    public ResponseEntity<?> createHistoriaClinica(@RequestBody HistoriaClinica historiaClinica) {
        if (historiaClinica.getPaciente() == null) {
            return ResponseEntity.badRequest().body("El campo 'paciente' es obligatorio");
        }

        if (historiaClinica.getFechaCreacion() == null) {
            historiaClinica.setFechaCreacion(LocalDateTime.now());
        }

        return ResponseEntity.ok(historiaClinicaService.save(historiaClinica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> updateHistoriaClinica(@PathVariable Long id, @RequestBody HistoriaClinica historiaClinica) {
        if (historiaClinicaService.findById(id).isPresent()) {
            return ResponseEntity.ok(historiaClinicaService.update(id, historiaClinica));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriaClinica(@PathVariable Long id) {
        if (historiaClinicaService.findById(id).isPresent()) {
            historiaClinicaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
