package com.deltaA.SuperrDuperr.controller;

import com.deltaA.SuperrDuperr.entity.ToDoList;
import com.deltaA.SuperrDuperr.exception.NotFoundException;
import com.deltaA.SuperrDuperr.service.SuperDuperToDoItemsService;
import com.deltaA.SuperrDuperr.service.SuperDuperToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SuperDuperToDoRestController {

    @Autowired
    SuperDuperToDoService superDuperToDoService;

    @Autowired
    SuperDuperToDoItemsService superDuperToDoItemsService;

    @GetMapping(path = "/tasks")
    public ResponseEntity<List<ToDoList>> getAllToDoTasks() {
        return new ResponseEntity<>(superDuperToDoService.allToDoTasks(), HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{id}")
    public ResponseEntity<ToDoList> getToDosByTaskID(@PathVariable("id") int id) throws NotFoundException {
        return new ResponseEntity<ToDoList>(superDuperToDoService.getToDoTask(id), HttpStatus.OK);
    }

    @PostMapping(path = "/tasks")
    public ResponseEntity createToDo(@RequestBody ToDoList toDo) {
        return new ResponseEntity<>(superDuperToDoService.createToDoTask(toDo), HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity updateToDo(@PathVariable int id,  @RequestBody ToDoList toDoListRequest) throws NotFoundException {
        return new ResponseEntity<>(superDuperToDoService.updateToDoTask(id, toDoListRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deletePost(@PathVariable int id) throws NotFoundException {
        superDuperToDoService.deleteToDoTask(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
