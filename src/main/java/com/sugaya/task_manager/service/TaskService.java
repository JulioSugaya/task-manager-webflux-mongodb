package com.sugaya.task_manager.service;

import com.sugaya.task_manager.entity.Task;
import com.sugaya.task_manager.exception.ResourceNotFoundException;
import com.sugaya.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Mono<Task> findById(String id) {
        return taskRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Task Id: " + id + " not found.")));
    }

    public Flux<Task> getAll() {
        return taskRepository.findAll();
    }

    public Mono<Task> createTask(Task task) {
        return taskRepository.save(task);
    }

    public Mono<Void> deleteTask(String id) {
        return findById(id).flatMap( task -> taskRepository.delete(task));
    }

    public Mono<Task> updateTask(Task task) {
        return this.findById(task.getId())
                .flatMap(task1 -> {
                    task1.setStatus(task.getStatus());
                    task1.setPriority(task.getPriority());
                    task1.setDueDate(task.getDueDate());
                    task1.setTitle(task.getTitle());
                    task1.setDueDate(task.getDueDate());
                    task1.setDescription(task.getDescription());

                    return taskRepository.save(task1);
                });
    }
}
