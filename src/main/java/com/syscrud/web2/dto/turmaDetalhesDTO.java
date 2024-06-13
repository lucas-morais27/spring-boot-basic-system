package com.syscrud.web2.dto;

import java.util.List;

// TurmaDetalhesDTO.java
public record turmaDetalhesDTO(
        Long id,
        String nome,
        String codigo,
        List<alunoNomeDTO> alunos,
        String professor
) {}
