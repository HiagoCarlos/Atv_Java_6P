package com.atvaula11.atvaula11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.atvaula11.atvaula11.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}