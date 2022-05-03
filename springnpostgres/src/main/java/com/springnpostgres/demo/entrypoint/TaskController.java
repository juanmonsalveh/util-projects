package com.springnpostgres.demo.entrypoint;

import com.springnpostgres.demo.core.TaskStatus;
import com.springnpostgres.demo.core.dto.TaskDto;
import com.springnpostgres.demo.model.entity.Task;
import com.springnpostgres.demo.model.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TaskController {
    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }


    @PostMapping("/tasks")
    public ResponseEntity createOneTask(
            @RequestBody TaskDto taskDto
    ) {
        Task task = new Task(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        return new ResponseEntity(repository.save(task).getId(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> readOneTask(
            @PathVariable Long id
    ){
        try{
            return new ResponseEntity(
                    repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new).toDto(),
                    HttpStatus.OK
            );
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity updateOneTask(
            @PathVariable Long id,
            @RequestBody TaskDto taskDto
    ){
        try{
            Task task = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            task.setDescription(taskDto.getDescription());
            task.setTitle(taskDto.getTitle());
            task.setTaskStatus(TaskStatus.valueOf(taskDto.getStatus()));
            repository.save(task);
            return new ResponseEntity( HttpStatus.OK );
        } catch (IllegalArgumentException e){
            return new ResponseEntity("Available statuses are: CREATED,APPROVED,REJECTED,BLOCKED,DONE.",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteOneTask(
            @PathVariable Long id
    ) {
        try{
            repository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/tasks/describe/{id}")
    public ResponseEntity describeOneTask(
            @PathVariable Long id
    ) {
        String description = "";
        try{
            TaskDto taskDto = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new).toDto();
            description = "Description of Task ["+id+": "+taskDto.getTitle()+"] is: "+ taskDto.getDescription();
            String[] result = {description};
            return new ResponseEntity(
                    result,
                    HttpStatus.OK
            );
        } catch (Exception e){
            description = "Task with id = "+id+" does not exist";
            String[] result = {description};
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity findAllTasks() {
        List<Task> allTasks = new ArrayList<Task>();
        repository.findAll().forEach(allTasks::add);
        return new ResponseEntity(
                allTasks,
                HttpStatus.OK
        );
    }

    @GetMapping("/tasks/describe")
    public ResponseEntity describeAllTasks() {
        List<String> allDescriptions = new ArrayList<String>();
        repository.findAll().forEach(it->
                allDescriptions.add(
                        "Description of Task ["+it.toDto().getId()+": "+it.toDto().getTitle()+"] is: "+it.toDto().getDescription()
                )
        );
        return new ResponseEntity(
                allDescriptions,
                HttpStatus.OK
        );
    }

}
