package com.syscrud.web2.repository;

import com.syscrud.web2.model.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    @Query("SELECT a FROM AlunoEntity a WHERE a.status = true")
    List<AlunoEntity> findAllByStatus(boolean status);
}
