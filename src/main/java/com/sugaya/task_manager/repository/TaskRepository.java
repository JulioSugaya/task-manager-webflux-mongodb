package com.sugaya.task_manager.repository;

import com.sugaya.task_manager.entity.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

}
