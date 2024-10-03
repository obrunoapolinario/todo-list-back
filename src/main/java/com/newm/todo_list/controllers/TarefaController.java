package com.newm.todo_list.controllers;

import com.newm.todo_list.domain.tarefa.Tarefa;
import com.newm.todo_list.domain.tarefa.TarefaDTO;
import com.newm.todo_list.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;
    TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }
    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TarefaDTO(novaTarefa));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        return ResponseEntity.status(HttpStatus.OK)
                .body(tarefas.stream()
                .map(TarefaDTO::new)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarTarefaPorId(@PathVariable UUID id) {
        Optional<Tarefa> tarefa = tarefaService.buscarTarefaPorId(id);
        return tarefa.map(t -> ResponseEntity.ok(new TarefaDTO(t))).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefa(@PathVariable UUID id, @RequestBody Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefa = tarefaService.atualizarTarefa(id, tarefaAtualizada);
        return tarefa.map(t -> ResponseEntity.ok(new TarefaDTO(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefaParcial(@PathVariable UUID id, @RequestBody Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefa = tarefaService.atualizarTarefaParcial(id, tarefaAtualizada);
        return tarefa.map(t -> ResponseEntity.ok(new TarefaDTO(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable UUID id) {
        boolean removido = tarefaService.deletarTarefa(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
