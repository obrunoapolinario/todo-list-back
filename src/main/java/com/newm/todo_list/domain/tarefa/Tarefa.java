package com.newm.todo_list.domain.tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "tarefa")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue
    private UUID id;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa status = StatusTarefa.NAO_INICIADO;
}
