package com.syscrud.web2.controller;

import com.syscrud.web2.dto.alunoNomeDTO;
import com.syscrud.web2.dto.turmaDetalhesDTO;
import com.syscrud.web2.dto.turmaDTO;
import com.syscrud.web2.model.TurmaEntity;
import com.syscrud.web2.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaEntity>> getAll() {
        return ResponseEntity.ok(turmaService.getAllTurmas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<turmaDetalhesDTO> getById(@PathVariable Long id) {
        Optional<TurmaEntity> turmaOptional = turmaService.getTurmaById(id);
        if (turmaOptional.isPresent()) {
            TurmaEntity turma = turmaOptional.get();
            String nomeProfessor = turma.getProfessor() != null ? turma.getProfessor().getNome() : null;

            List<alunoNomeDTO> alunosDTO = turma.getAlunos().stream()
                    .map(aluno -> new alunoNomeDTO(aluno.getNome()))
                    .collect(Collectors.toList());

            turmaDetalhesDTO turmaDTO = new turmaDetalhesDTO(turma.getId(), turma.getNome(), turma.getCodigo(), alunosDTO, nomeProfessor);
            return ResponseEntity.ok(turmaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Object> postTurma(@Valid @RequestBody turmaDTO turmaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.createTurma(turmaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaEntity> putTurma(@PathVariable Long id, @RequestBody turmaDTO turmaDTO) {
        Optional<TurmaEntity> turmaAtualizada = Optional.ofNullable(turmaService.updateTurma(id, turmaDTO));
        return turmaAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        turmaService.deleteTurma(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inactivate/{id}")
    public ResponseEntity<Object> logicDeleteAluno(@PathVariable(value = "id") Long id) {
        Optional<TurmaEntity> inactivatedTurma = turmaService.inactivateTurma(id);
        return (inactivatedTurma != null)
                ? ResponseEntity.status(HttpStatus.CREATED).body(inactivatedTurma)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

    @PostMapping("/{turmaId}/matricular/{alunoId}")
    public ResponseEntity<TurmaEntity> matricularAluno(@PathVariable Long turmaId, @PathVariable Long alunoId) {
        Optional<TurmaEntity> turmaAtualizada = turmaService.matricularAluno(turmaId, alunoId);
        return turmaAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{turmaId}/desmatricular/{alunoId}")
    public ResponseEntity<TurmaEntity> removerAluno(@PathVariable Long turmaId, @PathVariable Long alunoId) {
        Optional<TurmaEntity> turmaAtualizada = turmaService.removerAluno(turmaId, alunoId);
        return turmaAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{turmaId}/associar/{professorId}")
    public ResponseEntity<TurmaEntity> associarProfessor(@PathVariable Long turmaId, @PathVariable Long professorId) {
        Optional<TurmaEntity> turmaAtualizada = turmaService.associarProfessor(turmaId, professorId);
        return turmaAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{turmaId}/remover-professor")
    public ResponseEntity<TurmaEntity> removerProfessor(@PathVariable Long turmaId) {
        Optional<TurmaEntity> turmaAtualizada = turmaService.removerProfessor(turmaId);
        return turmaAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
