package com.perinity.manager.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "Tarefas")
public class Task {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Titulo")
    private String title;

    @Column(name = "Descricao")
    private String description;

    @Column(name = "Prazo")
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Department department;

    @Column(name = "Duracao")
    private Long duration;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Person person;

    @Column(name = "Finalizado")
    private boolean isDone;
}
