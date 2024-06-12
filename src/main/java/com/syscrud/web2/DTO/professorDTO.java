package com.syscrud.web2.dto;
import com.syscrud.web2.model.ProfessorEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Valid
public record professorDTO(

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
    ProfessorEntity.Genero genero,

    @NotBlank(message = "Departamento não pode ser vazio")
    @Size(min = 3, max = 50, message = "Departamento deve ter entre 3 e 50 caracteres")
    String departamento,

    @NotNull(message = "Data de nascimento não pode ser nula")
    @Past(message = "Data de nascimento deve ser no passado")
    Date dataNascimento,

    @Positive(message = "Salário deve ser um valor positivo")
    float salario,

    @NotBlank(message = "Disciplina associada não pode ser vazia")
    @Size(min = 3, max = 100, message = "Departamento deve ter entre 3 e 100 caracteres")
    String disciplinaAssociada,

    boolean status

    ) {

}
