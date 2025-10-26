package com.atvaula11.atvaula11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atvaula11.atvaula11.model.Professor;
import com.atvaula11.atvaula11.repository.ProfessorRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }
}