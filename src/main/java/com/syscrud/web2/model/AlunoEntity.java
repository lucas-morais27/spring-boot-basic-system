package com.syscrud.web2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.syscrud.web2.dto.alunoDTO;

@Entity
@Table(name="alunos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String cpf;
    private int matricula;
    private Genero genero;
    private String curso;
    private Date dataNascimento;
    private boolean status = true;

    public void filterEmptyFields(alunoDTO aluno) {
        if (aluno.nome() != null && !aluno.nome().isEmpty()) {
            this.nome = aluno.nome();
        }
        if (aluno.cpf() != null && !aluno.cpf().isEmpty()) {
            this.cpf = aluno.cpf();
        }
        if (aluno.matricula() != 0) {
            this.matricula = aluno.matricula();
        }
        if (aluno.genero() != null) {
            this.genero = aluno.genero();
        }
        if (aluno.curso() != null && !aluno.curso().isEmpty()) {
            this.curso = aluno.curso();
        }
        if (aluno.dataNascimento() != null) {
            this.dataNascimento = aluno.dataNascimento();
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
