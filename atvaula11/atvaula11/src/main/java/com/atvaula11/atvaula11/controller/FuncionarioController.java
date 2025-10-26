package com.atvaula11.atvaula11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.atvaula11.atvaula11.model.Funcionario;
import com.atvaula11.atvaula11.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
        return funcionarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        // Em um projeto real, você trataria o departamento aqui
        return funcionarioService.save(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetails) {
        return funcionarioService.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(funcionarioDetails.getNome());
                    funcionario.setCargo(funcionarioDetails.getCargo());
                    funcionario.setSalario(funcionarioDetails.getSalario());
                    funcionario.setDepartamento(funcionarioDetails.getDepartamento()); // Cuidado ao atualizar objetos relacionados
                    return ResponseEntity.ok(funcionarioService.save(funcionario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        if (!funcionarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        funcionarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}