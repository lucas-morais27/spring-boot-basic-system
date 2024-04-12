package com.syscrud.web2.DTO;
import com.syscrud.web2.model.ProfessorEntity;
import java.util.Date;

public record professorDTO(

    Long id,
    String nome,
    String cpf,
    int matricula,
    ProfessorEntity.Genero genero,
    String departamento,
    Date dataNascimento,
    float salario,
    String disciplinaAssociada

    ) {

}
