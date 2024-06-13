package com.syscrud.web2.service;

import com.syscrud.web2.dto.turmaDTO;
import com.syscrud.web2.exceptions.ResourceNotFoundException;
import com.syscrud.web2.model.AlunoEntity;
import com.syscrud.web2.model.ProfessorEntity;
import com.syscrud.web2.model.TurmaEntity;
import com.syscrud.web2.repository.AlunoRepository;
import com.syscrud.web2.repository.ProfessorRepository;
import com.syscrud.web2.repository.TurmaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<TurmaEntity> getAllTurmas() {
        return turmaRepository.findAll();
    }

    @Override
    public Optional<TurmaEntity> getTurmaById(Long id) {
        return turmaRepository.findById(id);
    }

    @Override
    public TurmaEntity createTurma(turmaDTO turmaDTO) {
        TurmaEntity turma = new TurmaEntity();
        BeanUtils.copyProperties(turmaDTO, turma);
        turma.setStatus(true);
        return turmaRepository.save(turma);
    }

    @Override
    public Optional<TurmaEntity> updateTurma(Long id, turmaDTO turmaDTO) {
        return turmaRepository.findById(id)
                .map(turmaExistente -> {
                    BeanUtils.copyProperties(turmaDTO, turmaExistente);
                    return turmaRepository.save(turmaExistente);
                });
    }

    @Override
    public void deleteTurma(Long id) {
        turmaRepository.deleteById(id);
    }

    @Override
    public Optional<TurmaEntity> inactivateTurma(Long id) {
        Optional<TurmaEntity> existingAluno = turmaRepository.findById(id);
        if (existingAluno.isPresent()) {
            TurmaEntity alunoEntity = existingAluno.get();
            alunoEntity.setStatus(false);
            return Optional.of(turmaRepository.save(alunoEntity));
        }
        return null; // Or throw an exception
    }

    @Override
    public Optional<TurmaEntity> matricularAluno(Long turmaId, Long alunoId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            AlunoEntity aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + alunoId));
            turma.getAlunos().add(aluno);
            return turmaRepository.save(turma);
        });
    }

    @Override
    public Optional<TurmaEntity> removerAluno(Long turmaId, Long alunoId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            turma.getAlunos().removeIf(aluno -> aluno.getId().equals(alunoId));
            return turmaRepository.save(turma);
        });
    }

    @Override
    public Optional<TurmaEntity> associarProfessor(Long turmaId, Long professorId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            ProfessorEntity professor = professorRepository.findById(professorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + professorId));
            turma.setProfessorDisciplina(professor);
            return turmaRepository.save(turma);
        });
    }

    @Override
    public Optional<TurmaEntity> removerProfessor(Long turmaId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            turma.setProfessorDisciplina(null);
            return turmaRepository.save(turma);
        });
    }
}
