package com.atvaula11.atvaula11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atvaula11.atvaula11.model.Aluno;
import com.atvaula11.atvaula11.repository.AlunoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }
}