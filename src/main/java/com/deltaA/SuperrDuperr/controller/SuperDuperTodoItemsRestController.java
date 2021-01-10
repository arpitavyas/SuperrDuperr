package com.deltaA.SuperrDuperr.controller;

import com.deltaA.SuperrDuperr.entity.Tags;
import com.deltaA.SuperrDuperr.entity.ToDoListItem;
import com.deltaA.SuperrDuperr.exception.NotFoundException;
import com.deltaA.SuperrDuperr.service.SuperDuperToDoItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SuperDuperTodoItemsRestController {

    @Autowired
    SuperDuperToDoItemsService superDuperToDoItemsService;


    @GetMapping(path = "/tasks/{id}/items")
    public ResponseEntity<List<ToDoListItem>> ToDoListItems(@PathVariable("id") int taskId) {
        return new ResponseEntity(superDuperToDoItemsService.allToDoTaskItems(taskId), HttpStatus.OK);
    }

    @GetMapping(path = "/tasks/{taskId}/items/{itemId}")
    public ResponseEntity<List<ToDoListItem>> getToDoListItems(@PathVariable (value = "taskId") int taskId,
                                                               @PathVariable (value = "itemId") int itemId) {
        return new ResponseEntity(superDuperToDoItemsService.getToDoTaskItem(taskId, itemId), HttpStatus.OK);
    }

    @PostMapping(path = "/tasks/{id}/items")
    public ResponseEntity<List<ToDoListItem>> createToDoListItem(@PathVariable("id") int id,
                                                                 @RequestBody ToDoListItem item) throws NotFoundException {
        return new ResponseEntity(superDuperToDoItemsService.createToDoTaskItem(id, item), HttpStatus.CREATED);
    }

    @PutMapping(path = "/tasks/{taskId}/items/{itemId}")
    public ResponseEntity restoreToDoItem(@PathVariable (value = "taskId") int id,
                                          @PathVariable (value = "itemId") int itemId,
                                          @RequestBody ToDoListItem toDoListRequest) throws NotFoundException {
        return new ResponseEntity<>(superDuperToDoItemsService.updateToDoTaskItem(id, itemId, toDoListRequest), HttpStatus.OK);
    }

    @DeleteMapping(path = "/tasks/{taskId}/items/{itemId}")
    public ResponseEntity deleteToDoItem(@PathVariable (value = "taskId") int id,
                                         @PathVariable (value = "itemId") int itemId) throws NotFoundException {

        superDuperToDoItemsService.deleteToDoTaskItem(id, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    add tag to TODO item
    @PostMapping(path = "/tasks/{taskId}/items/{itemId}")
    public ResponseEntity<List<ToDoListItem>> addTagToListItem(@PathVariable (value = "taskId") int taskid,
                                                               @PathVariable (value = "itemId") int itemid,
                                                               @RequestBody Tags tag) throws NotFoundException {
        return new ResponseEntity(superDuperToDoItemsService.addTagToDoTaskItem(taskid, itemid, tag), HttpStatus.CREATED);
    }
}
