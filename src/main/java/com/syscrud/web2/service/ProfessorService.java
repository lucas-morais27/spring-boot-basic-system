package com.syscrud.web2.service;

import com.syscrud.web2.dto.professorDTO;
import com.syscrud.web2.model.ProfessorEntity;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<ProfessorEntity> getAllActiveProfessors();
    Optional<ProfessorEntity> getProfessorById(Long id);
    ProfessorEntity createProfessor(professorDTO professorDTO);
    ProfessorEntity updateProfessor(Long id, professorDTO professorDTO);
    void deleteProfessor(Long id);
    ProfessorEntity inactivateProfessor(Long id);
}