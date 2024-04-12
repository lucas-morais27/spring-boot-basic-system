package com.syscrud.web2.model;

import com.syscrud.web2.DTO.professorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="professores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorEntity {

    public ProfessorEntity(professorDTO professor){
        this.nome = professor.nome();
        this.cpf = professor.cpf();
        this.matricula = professor.matricula();
        this.genero = professor.genero();
        this.dataNascimento = professor.dataNascimento();
        this.salario = professor.salario();
        this.disciplinaAssociada = professor.disciplinaAssociada();
    }

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String cpf;
    private int matricula;
    private Genero genero;
    private String departamento;
    private Date dataNascimento;
    private float salario;
    private String disciplinaAssociada;

    public enum Genero {
        masculino,
        feminino;
    }
}

