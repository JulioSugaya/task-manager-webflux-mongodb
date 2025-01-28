package com.sugaya.task_manager.controller;

import com.sugaya.task_manager.dto.TaskDTO;
import com.sugaya.task_manager.entity.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TaskMapper {

    Mono<TaskDTO> toDTO(Mono<Task> task) {
        return task.map(this::toDTO);
    }

    Mono<Task> toEntity(Mono<TaskDTO> dto) {
        return dto.map(this::toEntity);
    }

    Flux<TaskDTO> toDTO(Flux<Task> task) {
        return task.map(this::toDTO);
    }

    Flux<Task> toEntity(Flux<TaskDTO> dto) {
        return dto.map(this::toEntity);
    }

    TaskDTO toDTO(Task task) {
        TaskDTO newDto = new TaskDTO();
        BeanUtils.copyProperties(task, newDto);
        return newDto;
    }

    Task toEntity(TaskDTO dto) {
        Task newTask = new Task();
        BeanUtils.copyProperties(dto, newTask);
        return newTask;
    }
}
