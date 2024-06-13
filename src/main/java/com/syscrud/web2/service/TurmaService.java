package com.syscrud.web2.service;

import com.syscrud.web2.dto.alunoDTO;
import com.syscrud.web2.dto.turmaDTO;
import com.syscrud.web2.model.TurmaEntity;

import java.util.List;
import java.util.Optional;

public interface TurmaService {
    List<TurmaEntity> getAllTurmas();

    Optional<TurmaEntity> getTurmaById(Long id);

    Optional<TurmaEntity> removerProfessor(Long turmaId);

    Optional<TurmaEntity> associarProfessor(Long turmaId, Long professorId);

    Optional<TurmaEntity> removerAluno(Long turmaId, Long alunoId);

    Optional<TurmaEntity> matricularAluno(Long turmaId, Long alunoId);

    Optional<TurmaEntity> inactivateTurma(Long id);

    TurmaEntity updateTurma(Long id, turmaDTO turmaDTO);

    void deleteTurma(Long id);

    Object createTurma(turmaDTO turmaDTO);
}
