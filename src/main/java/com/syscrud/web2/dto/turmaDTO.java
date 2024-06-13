package com.syscrud.web2.dto;

import com.syscrud.web2.model.AlunoEntity;
import com.syscrud.web2.model.ProfessorEntity;
import com.syscrud.web2.model.TurmaEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Valid
public record turmaDTO(

    Long id,

    @NotBlank(message = "Nome da turma é obrigatório")
    @Size(min = 3, max = 100, message = "Nome da turma deve ter entre 3 e 100 caracteres")
    String nome,

    @NotBlank(message = "Código da turma é obrigatório")
    @Size(min = 3, max = 10, message = "Código da turma deve ter entre 3 e 10 caracteres")
    String codigo,

    List<AlunoEntity> alunos,

    ProfessorEntity professorDisciplina

) {
}
