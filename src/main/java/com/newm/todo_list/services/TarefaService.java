package com.newm.todo_list.services;

import com.newm.todo_list.domain.tarefa.Tarefa;
import com.newm.todo_list.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaService {
    private TarefaRepository tarefaRepository;

    TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarTarefaPorId(UUID id) {
        return tarefaRepository.findById(id);
    }
    
    public Optional<Tarefa> atualizarTarefa(UUID id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);
        
        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            tarefa.setTitulo(tarefaAtualizada.getTitulo());
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setStatus(tarefaAtualizada.getStatus());
            
            return Optional.of(tarefaRepository.save(tarefa));
        }
        
        return Optional.empty();
    }
    
    public Optional<Tarefa> atualizarTarefaParcial(UUID id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistente = buscarTarefaPorId(id);
        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            if (tarefaAtualizada.getTitulo() != null) {
                tarefa.setTitulo(tarefaAtualizada.getTitulo());
            }
            if (tarefaAtualizada.getDescricao() != null) {
                tarefa.setDescricao(tarefaAtualizada.getDescricao());
            }
            if (tarefaAtualizada.getStatus() != null) {
                tarefa.setStatus(tarefaAtualizada.getStatus());
            }
            return Optional.of(tarefaRepository.save(tarefa));
        }
        return Optional.empty();
    }

    public boolean deletarTarefa(UUID id) {
        Optional<Tarefa> tarefa = buscarTarefaPorId(id);
        if (tarefa.isPresent()) {
            tarefaRepository.delete(tarefa.get());
            return true;
        }
        return false;
    }
}
