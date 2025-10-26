package com.atvaula11.atvaula11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.atvaula11.atvaula11.model.Aluno;
import com.atvaula11.atvaula11.service.AlunoService;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        return alunoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDetails) {
        return alunoService.findById(id)
                .map(aluno -> {
                    aluno.setNome(alunoDetails.getNome());
                    aluno.setMatricula(alunoDetails.getMatricula());
                    aluno.setCurso(alunoDetails.getCurso());
                    return ResponseEntity.ok(alunoService.save(aluno));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        if (!alunoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}