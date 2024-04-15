package com.syscrud.web2.controller;

import com.syscrud.web2.DTO.alunoDTO;
import com.syscrud.web2.model.AlunoEntity;
import com.syscrud.web2.repository.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByStatus(true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id){
        Optional<AlunoEntity> alunoEntity = repository.findById(id);
        if (alunoEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunoEntity.get());
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> postAluno(@RequestBody alunoDTO aluno) {
        AlunoEntity alunoEntity = new AlunoEntity();
        BeanUtils.copyProperties(aluno, alunoEntity);
        alunoEntity.setStatus(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(alunoEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putAluno(@PathVariable(value = "id") Long id,
                                         @RequestBody alunoDTO aluno){
        Optional<AlunoEntity> alunoEntity = repository.findById(id);
        if (alunoEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        AlunoEntity alunoModel = alunoEntity.get();
        alunoModel.filterEmptyFields(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(alunoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAluno(@PathVariable(value = "id") Long id){
        Optional<AlunoEntity> alunoEntity = repository.findById(id);
        if(alunoEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        repository.delete(alunoEntity.get());
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully!");
    }

    @DeleteMapping("/inactivate/{id}")
    public ResponseEntity<Object> logicDeleteAluno(@PathVariable(value = "id") Long id){
        Optional<AlunoEntity> alunoEntity = repository.findById(id);
        if (alunoEntity.isPresent()) {
            AlunoEntity aluno = alunoEntity.get();
            aluno.setStatus(false);
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(aluno));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

}
