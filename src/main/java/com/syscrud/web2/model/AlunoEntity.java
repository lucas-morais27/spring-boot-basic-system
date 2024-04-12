package com.syscrud.web2.model;

import com.syscrud.web2.DTO.alunoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name="alunos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoEntity {

    public AlunoEntity(alunoDTO aluno){
        this.nome = aluno.nome();
        this.cpf = aluno.cpf();
        this.matricula = aluno.matricula();
        this.genero = aluno.genero();
        this.curso = aluno.curso();
        this.dataNascimento = aluno.dataNascimento();
    }

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String cpf;
    private int matricula;
    private Genero genero;
    private String curso;
    private Date dataNascimento;

    public void filterEmptyFields(alunoDTO aluno) {

    }

    public enum Genero {
        masculino,
        feminino;
    }
}
