package com.syscrud.web2.dto;

import com.syscrud.web2.model.ProfessorEntity;

import java.util.Date;
import java.util.List;

public record ProfessorComNomesTurmasDTO(
        Long id,
        String nome,
        String cpf,
        int matricula,
        ProfessorEntity.Genero genero,
        String departamento,
        Date dataNascimento,
        float salario,
        String disciplinaAssociada,
        List<String> turmas // Lista de nomes das turmas
) {}
