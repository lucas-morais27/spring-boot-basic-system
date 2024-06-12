package com.syscrud.web2.service;

import com.syscrud.web2.dto.alunoDTO;
import com.syscrud.web2.model.AlunoEntity;
import com.syscrud.web2.repository.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public List<AlunoEntity> getAllActiveStudents() {
        return alunoRepository.findAllByStatus(true);
    }

    @Override
    public Optional<AlunoEntity> getStudentById(Long id) {
        return alunoRepository.findById(id);
    }

    @Override
    public AlunoEntity createStudent(alunoDTO alunoDTO) {
        AlunoEntity alunoEntity = new AlunoEntity();
        BeanUtils.copyProperties(alunoDTO, alunoEntity);
        alunoEntity.setStatus(true);
        return alunoRepository.save(alunoEntity);
    }

    @Override
    public AlunoEntity updateStudent(Long id, alunoDTO alunoDTO) {
        Optional<AlunoEntity> existingAluno = alunoRepository.findById(id);
        if (existingAluno.isPresent()) {
            AlunoEntity alunoEntity = existingAluno.get();
            alunoEntity.filterEmptyFields(alunoDTO); // Assuming this method exists in AlunoEntity
            return alunoRepository.save(alunoEntity);
        }
        return null; // Or throw an exception
    }

    @Override
    public void deleteStudent(Long id) {
        alunoRepository.deleteById(id);
    }

    @Override
    public AlunoEntity inactivateStudent(Long id) {
        Optional<AlunoEntity> existingAluno = alunoRepository.findById(id);
        if (existingAluno.isPresent()) {
            AlunoEntity alunoEntity = existingAluno.get();
            alunoEntity.setStatus(false);
            return alunoRepository.save(alunoEntity);
        }
        return null; // Or throw an exception
    }
}
