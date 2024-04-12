package com.syscrud.web2.DTO;
import com.syscrud.web2.model.AlunoEntity;
import java.util.Date;

public record alunoDTO(

    Long id,
    String nome,
    String cpf,
    int matricula,
    AlunoEntity.Genero genero,
    String curso,
    Date dataNascimento

    ) {

}
