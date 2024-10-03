package com.newm.todo_list.domain.tarefa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarefaDTO {
    private String id;
    private String titulo;
    private String descricao;
    private String status;

    public TarefaDTO(Tarefa tarefa) {
        this.id = tarefa.getId().toString();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.status = tarefa.getStatus().toString();
    }
}
