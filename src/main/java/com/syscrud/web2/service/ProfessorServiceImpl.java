package com.syscrud.web2.service;
import com.syscrud.web2.dto.professorDTO;
import com.syscrud.web2.exceptions.ProfessorNotFoundException;
import com.syscrud.web2.model.ProfessorEntity;
import com.syscrud.web2.repository.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<ProfessorEntity> getAllActiveProfessors() {
        return professorRepository.findAllByStatus(true);
    }

    @Override
    public Optional<ProfessorEntity> getProfessorById(Long id) {
        return professorRepository.findById(id);
    }

    @Override
    public ProfessorEntity createProfessor(professorDTO professorDTO) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        BeanUtils.copyProperties(professorDTO, professorEntity);
        professorEntity.setStatus(true);
        return professorRepository.save(professorEntity);
    }

    @Override
    public ProfessorEntity updateProfessor(Long id, professorDTO professorDTO) {
        ProfessorEntity professorEntity = professorRepository.findById(id).orElseThrow(()-> new ProfessorNotFoundException(id));
        professorEntity.filterEmptyFields(professorDTO);
        return professorRepository.save(professorEntity);
    }

    @Override
    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }

    @Override
    public ProfessorEntity inactivateProfessor(Long id) {
        ProfessorEntity professorEntity = professorRepository.findById(id).orElseThrow(()-> new ProfessorNotFoundException(id));
        professorEntity.setStatus(false);
        return professorRepository.save(professorEntity);
    }
}


