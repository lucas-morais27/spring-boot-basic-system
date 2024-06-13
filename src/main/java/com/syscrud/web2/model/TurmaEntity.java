package com.syscrud.web2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syscrud.web2.dto.alunoDTO;
import com.syscrud.web2.dto.turmaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "turmas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String codigo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "turmas_alunos",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<AlunoEntity> alunos;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;

    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setProfessorDisciplina(ProfessorEntity professor) {
        this.professor = professor;
    }

    public void filterEmptyFields(turmaDTO turma) {
        if (turma.nome() != null && !turma.nome().isEmpty()) {
            this.nome = turma.nome();
        }
        if (turma.codigo() != null && !turma.codigo().isEmpty()) {
            this.codigo = turma.codigo();
        }
        if (turma.alunos() != null && !turma.alunos().isEmpty()) {
            this.alunos = turma.alunos();
        }
        if (turma.professorDisciplina() != null) {
            this.professor = turma.professorDisciplina();
        }

    }
}
