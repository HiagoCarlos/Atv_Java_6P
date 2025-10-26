package com.atvaula11.atvaula11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.atvaula11.atvaula11.model.Departamento;
import com.atvaula11.atvaula11.service.DepartamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos") // URL base para este controller
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping // GET /api/departamentos
    public List<Departamento> getAllDepartamentos() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}") // GET /api/departamentos/1
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Long id) {
        return departamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/departamentos
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoService.save(departamento);
    }

    @PutMapping("/{id}") // PUT /api/departamentos/1
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable Long id, @RequestBody Departamento departamentoDetails) {
        return departamentoService.findById(id)
                .map(departamento -> {
                    departamento.setNome(departamentoDetails.getNome());
                    departamento.setSigla(departamentoDetails.getSigla());
                    return ResponseEntity.ok(departamentoService.save(departamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // DELETE /api/departamentos/1
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long id) {
        if (!departamentoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        departamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}