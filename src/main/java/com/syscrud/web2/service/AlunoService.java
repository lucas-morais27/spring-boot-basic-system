package com.syscrud.web2.service;

import com.syscrud.web2.dto.alunoDTO;
import com.syscrud.web2.model.AlunoEntity;

import java.util.List;
import java.util.Optional;

public interface AlunoService {
    List<AlunoEntity> getAllActiveStudents();

    Optional<AlunoEntity> getStudentById(Long id);

    AlunoEntity createStudent(alunoDTO alunoDTO);

    AlunoEntity updateStudent(Long id, alunoDTO alunoDTO);

    void deleteStudent(Long id);

    AlunoEntity inactivateStudent(Long id);

}
