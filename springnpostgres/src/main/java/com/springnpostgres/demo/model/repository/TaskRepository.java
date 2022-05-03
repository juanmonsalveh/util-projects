package com.springnpostgres.demo.model.repository;


import com.springnpostgres.demo.model.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface TaskRepository extends CrudRepository<Task, Long> {
}
