package com.atvaula11.atvaula11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.atvaula11.atvaula11.model.Professor;
import com.atvaula11.atvaula11.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> getAllProfessores() {
        return professorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        return professorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.save(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professorDetails) {
        return professorService.findById(id)
                .map(professor -> {
                    professor.setNome(professorDetails.getNome());
                    professor.setEspecialidade(professorDetails.getEspecialidade());
                    professor.setDepartamento(professorDetails.getDepartamento());
                    return ResponseEntity.ok(professorService.save(professor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        if (!professorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}