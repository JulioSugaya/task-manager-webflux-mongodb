package com.sugaya.task_manager.controller;

import com.sugaya.task_manager.dto.TaskDTO;
import com.sugaya.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/task-manager")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskDTO>> getTaskById(@PathVariable String id) {
        return taskService.findById(id)
                .map(task -> new ResponseEntity<>(taskMapper.toDTO(task), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public Flux<TaskDTO> getAllTask() {
        return taskService.getAll().map(taskMapper::toDTO);
    }

    @PostMapping("/")
    public Mono<ResponseEntity<TaskDTO>> createTask(@RequestBody TaskDTO dto) {
        return taskService.createTask(taskMapper.toEntity(dto))
                .map(task -> new ResponseEntity<>(taskMapper.toDTO(task), HttpStatus.OK));
    }

    @PutMapping("/")
    public Mono<ResponseEntity<TaskDTO>> updateTask(@RequestBody TaskDTO dto) {
        return taskService.updateTask(taskMapper.toEntity(dto))
                .map(task -> new ResponseEntity<>(taskMapper.toDTO(task), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTask(@PathVariable String id) {
        return taskService.deleteTask(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
