package com.syscrud.web2.controller;

import com.syscrud.web2.dto.ProfessorComNomesTurmasDTO;
import com.syscrud.web2.dto.professorDTO;
import com.syscrud.web2.model.TurmaEntity;
import com.syscrud.web2.exceptions.ProfessorNotFoundException;
import com.syscrud.web2.model.ProfessorEntity;
import com.syscrud.web2.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getAllActiveProfessors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorComNomesTurmasDTO> getById(@PathVariable Long id) {
        Optional<ProfessorEntity> professorOptional = professorService.getProfessorById(id);
        if (professorOptional.isPresent()) {
            ProfessorEntity professor = professorOptional.get();
            List<String> nomesTurmas = professor.getTurmas().stream()
                    .map(TurmaEntity::getNome)
                    .collect(Collectors.toList());

            ProfessorComNomesTurmasDTO professorDTO = new ProfessorComNomesTurmasDTO(
                    professor.getId(),
                    professor.getNome(),
                    professor.getCpf(),
                    professor.getMatricula(),
                    professor.getGenero(),
                    professor.getDepartamento(),
                    professor.getDataNascimento(),
                    professor.getSalario(),
                    professor.getDisciplinaAssociada(),
                    nomesTurmas
            );
            return ResponseEntity.ok(professorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postProfessor(@Valid @RequestBody professorDTO professor, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        ProfessorEntity professorEntity = professorService.createProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProfessor(@PathVariable(value = "id") Long id,
                                               @RequestBody professorDTO professor) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(professorService.updateProfessor(id, professor));
        }catch (ProfessorNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfessor(@PathVariable(value = "id") Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted successfully!");
    }

    @DeleteMapping("inactivate/{id}")
    public ResponseEntity<Object> logicDeleteProfessor(@PathVariable(value = "id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(professorService.inactivateProfessor(id));
        }catch (ProfessorNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
