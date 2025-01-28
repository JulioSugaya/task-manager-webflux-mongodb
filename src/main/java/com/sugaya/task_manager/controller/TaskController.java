package com.sugaya.task_manager.controller;

import com.sugaya.task_manager.dto.TaskDTO;
import com.sugaya.task_manager.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Mono<TaskDTO> getTaskById(@PathVariable String id) {
        return taskService.findById(id).map(taskMapper::toDTO);
    }

    @Tag(name = "get", description = "GET All tasks")
    @GetMapping("/")
    public Flux<TaskDTO> getAllTask() {
        return taskService.getAll().map(taskMapper::toDTO);
    }

    @PostMapping("/")
    public Mono<TaskDTO> createTask(@RequestBody TaskDTO dto) {
        return taskService.createTask(taskMapper.toEntity(dto))
                .map(taskMapper::toDTO);
    }

    @PutMapping("/")
    public Mono<TaskDTO> updateTask(@RequestBody TaskDTO dto) {
        return taskService.updateTask(taskMapper.toEntity(dto))
                .map(taskMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

}
