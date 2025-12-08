package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Tratamiento;
import com.example.ClinicaDental.service.TratamientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")

public class TratamientoController {

    private final TratamientoService tratamientoService;

    public TratamientoController(TratamientoService tratamientoService) {
        this.tratamientoService = tratamientoService;
    }

    @GetMapping
    public List<Tratamiento> getAllTratamientos() {
        return tratamientoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> getTratamientoById(@PathVariable Long id) {
        return tratamientoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createTratamiento(@RequestBody Tratamiento tratamiento) {
        if (tratamiento.getNombre() == null || tratamiento.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'nombre' es obligatorio");
        }
        if (tratamiento.getPaciente() == null) {
            return ResponseEntity.badRequest().body("Debe asociar un 'paciente' al tratamiento");
        }
        if (tratamiento.getCosto() == null || tratamiento.getCosto() < 0) {
            return ResponseEntity.badRequest().body("El campo 'costo' es obligatorio y debe ser mayor o igual a 0");
        }

        return ResponseEntity.ok(tratamientoService.save(tratamiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamiento> updateTratamiento(@PathVariable Long id, @RequestBody Tratamiento tratamiento) {
        if (tratamientoService.findById(id).isPresent()) {
            return ResponseEntity.ok(tratamientoService.update(id, tratamiento));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTratamiento(@PathVariable Long id) {
        if (tratamientoService.findById(id).isPresent()) {
            tratamientoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
