package com.deltaA.SuperrDuperr.controller;


import com.deltaA.SuperrDuperr.entity.Tags;
import com.deltaA.SuperrDuperr.entity.ToDoList;
import com.deltaA.SuperrDuperr.entity.ToDoListItem;
import com.deltaA.SuperrDuperr.exception.NotFoundException;
import com.deltaA.SuperrDuperr.service.SuperDuperToDoItemsService;
import com.deltaA.SuperrDuperr.service.SuperDuperToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class SuperDuperToDosAppController {

    @Autowired
    SuperDuperToDoService superDuperToDoService;

    @Autowired
    SuperDuperToDoItemsService superDuperToDoItemsService;

    @GetMapping("/SuperDuperApp")
    public String listAllToDos(Model theModel) {
        theModel.addAttribute("todos", superDuperToDoService.allToDoTasks());
        return "MyToDo";
    }

    @GetMapping("/editToDo/{id}")
    public String showUpdateToDoForm(@PathVariable("id") int id, Model model) throws NotFoundException {
        ToDoList toDoList = superDuperToDoService.getToDoTask(id);
        model.addAttribute("todo", toDoList);
        return "editToDo";
    }

    @GetMapping("/deleteToDo/{id}")
    public String deletePost(@PathVariable("id") int id) throws NotFoundException {
       superDuperToDoService.deleteToDoTask(id);
       return "redirect:/SuperDuperApp";
    }

    @GetMapping("/viewToDo/{id}")
    public String viewTodo(@PathVariable("id") int id, Model model) throws NotFoundException {
        List<ToDoListItem> toDoList = superDuperToDoItemsService.allToDoTaskItems(id);
        model.addAttribute("listId", id);
        model.addAttribute("todoItems", toDoList);
        return "listTodoItems";
    }

    @PostMapping("/saveEditToDo")
    public String saveTodo(@ModelAttribute("todo") ToDoList toDoList) throws NotFoundException {
        superDuperToDoService.updateToDoTask(toDoList.getListId(), toDoList);
        return "redirect:/SuperDuperApp";
    }

    @GetMapping("/addToDo")
    public String addToDo(Model theModel) {
        ToDoList toDoList = new ToDoList();
        theModel.addAttribute("todo", toDoList);
        return "addTodo";
    }

    @PostMapping("/saveTodo")
    public String saveToDo(@ModelAttribute("todo") ToDoList toDoList) throws NotFoundException {
        superDuperToDoService.createToDoTask(toDoList);
        return "redirect:/SuperDuperApp";
    }


    @GetMapping("/addToDoItem/{id}")
    public String showFormForAddToDoItem(@PathVariable("id") int id,Model theModel) {
        ToDoListItem toDoListItem = new ToDoListItem();
        theModel.addAttribute("listId", id);
        theModel.addAttribute("todo", toDoListItem);
        return "addToDoItem";
    }

    @PostMapping("/saveTodoItem/{id}")
    public String saveToDoItem(@PathVariable("id") int id,
                               @ModelAttribute("todo") ToDoListItem toDoList) throws NotFoundException {
        superDuperToDoItemsService.createToDoTaskItem(id, toDoList);
        return "redirect:/viewToDo/{id}";
    }

    @GetMapping("/viewToDo/list/{listid}/editToDoItem/{id}")
    public String showFormForEditToDoItem(@PathVariable("listid") int listid,
                                          @PathVariable("id") int id, Model model) throws NotFoundException {
        ToDoListItem toDoList = superDuperToDoItemsService.getToDoTaskItem(listid, id);
        model.addAttribute("todo", toDoList);
        model.addAttribute("listId", listid);
        return "editToDoItem";
    }

    @PostMapping("/saveToDoItem/{listid}/itemid/{id}")
    public String saveEditedTodoItem(@PathVariable("listid") int listid,
                            @PathVariable("id") int id,
                            @ModelAttribute("todo") ToDoListItem toDoList) throws NotFoundException {

        superDuperToDoItemsService.updateToDoTaskItem(listid, id, toDoList);
        return "redirect:/viewToDo/{listid}";
    }

    @GetMapping("/viewToDo/list/{listid}/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable("listid") int listid,
                                @PathVariable("id") int id) throws NotFoundException {
        superDuperToDoItemsService.deleteToDoTaskItem(listid, id);
        return "redirect:/viewToDo/{listid}";
    }

    @GetMapping("/viewToDo/list/{listid}/addTagToDoItem/{id}")
    public String addTagToDoItem(@PathVariable("listid") int listid,
                                 @PathVariable("id") int id, Model model) throws NotFoundException {
        Tags tag = new Tags();
        model.addAttribute("listId", listid);
        model.addAttribute("id", id);
        model.addAttribute("todo", tag);
        return "addTagTodoItem";
    }

    @PostMapping("/viewToDo/{listid}/saveTagToDoItem/{id}")
    public String saveTagToDoItem(@PathVariable("listid") int listid,
                                  @PathVariable("id") int id, @ModelAttribute("todo") Tags tags) throws NotFoundException {

        superDuperToDoItemsService.addTagToDoTaskItem(listid, id, tags);
        return "redirect:/viewToDo/{listid}";
    }
}
