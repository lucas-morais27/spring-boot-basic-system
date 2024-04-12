package com.syscrud.web2.controller;

import com.syscrud.web2.DTO.alunoDTO;
import com.syscrud.web2.model.AlunoEntity;
import com.syscrud.web2.repository.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
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
        AlunoEntity alunoEntity = new AlunoEntity(aluno);
        BeanUtils.copyProperties(aluno, alunoEntity);
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
        BeanUtils.copyProperties(aluno, alunoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(alunoModel));
    }

}
