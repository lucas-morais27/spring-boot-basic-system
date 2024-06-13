package com.syscrud.web2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
}
