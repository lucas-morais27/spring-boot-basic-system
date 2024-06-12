package com.syscrud.web2.controller;

import com.syscrud.web2.dto.professorDTO;
import com.syscrud.web2.model.ProfessorEntity;
import com.syscrud.web2.repository.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorRepository repository;

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByStatus(true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id){
        Optional<ProfessorEntity> professorEntity = repository.findById(id);
        if (professorEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(professorEntity.get());
    }

    @PostMapping
    public ResponseEntity<ProfessorEntity> postProfessor(@RequestBody professorDTO professor) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        BeanUtils.copyProperties(professor, professorEntity);
        professorEntity.setStatus(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(professorEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProfessor(@PathVariable(value = "id") Long id,
                                           @RequestBody professorDTO professor){
        Optional<ProfessorEntity> professorEntity = repository.findById(id);
        if (professorEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
        }
        ProfessorEntity professorModel = professorEntity.get();
        professorModel.filterEmptyFields(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(professorModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfessor(@PathVariable(value = "id") Long id){
        Optional<ProfessorEntity> professorEntity = repository.findById(id);
        if(professorEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
        }
        repository.delete(professorEntity.get());
        return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted successfully!");
    }

    @DeleteMapping("inactivate/{id}")
    public ResponseEntity<Object> logicDeleteProfessor(@PathVariable(value = "id") Long id){
        Optional<ProfessorEntity> professorEntity = repository.findById(id);
        if (professorEntity.isPresent()) {
            ProfessorEntity professor = professorEntity.get();
            professor.setStatus(false);
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(professor));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found");
    }

}
