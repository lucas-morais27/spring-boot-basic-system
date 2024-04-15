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
    private boolean status = true;

    public void filterEmptyFields(professorDTO professor) {
        if (professor.nome() != null && !professor.nome().isEmpty()) {
            this.nome = professor.nome();
        }
        if (professor.cpf() != null && !professor.cpf().isEmpty()) {
            this.cpf = professor.cpf();
        }
        if (professor.matricula() != 0) {
            this.matricula = professor.matricula();
        }
        if (professor.genero() != null) {
            this.genero = professor.genero();
        }
        if (professor.departamento() != null && !professor.departamento().isEmpty()) {
            this.departamento = professor.departamento();
        }
        if (professor.dataNascimento() != null) {
            this.dataNascimento = professor.dataNascimento();
        }
        if (professor.salario() > 0) {
            this.salario = professor.salario();
        }
        if (professor.disciplinaAssociada() != null && !professor.disciplinaAssociada().isEmpty()) {
            this.disciplinaAssociada = professor.disciplinaAssociada();
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public enum Genero {
        masculino,
        feminino;
    }
}

