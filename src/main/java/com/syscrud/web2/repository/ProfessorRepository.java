package com.syscrud.web2.repository;

import com.syscrud.web2.model.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    @Query("SELECT a FROM ProfessorEntity a WHERE a.status = true")
    List<ProfessorEntity> findAllByStatus(boolean status);
}
