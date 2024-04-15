package com.syscrud.web2.DTO;
import com.syscrud.web2.model.AlunoEntity;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

public record alunoDTO(

    Long id,

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    String nome,

    @NotBlank(message = "CPF não pode ser vazio")
    @CPF
    String cpf,

    @Positive(message = "Matrícula deve ser um número positivo")
    int matricula,

    @NotNull(message = "Gênero não pode ser nulo")
    AlunoEntity.Genero genero,

    @NotBlank(message = "Curso não pode ser vazio")
    @Size(min = 3, max = 100, message = "Curso deve ter entre 3 e 100 caracteres")
    String curso,

    @NotBlank(message = "Data de nascimento não pode ser vazia")
    @Past(message = "Data de nascimento deve ser no passado")
    Date dataNascimento,

    boolean status
    ) {

}
