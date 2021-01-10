package com.deltaA.SuperrDuperr.service;

import com.deltaA.SuperrDuperr.entity.ToDoList;
import com.deltaA.SuperrDuperr.exception.NotFoundException;

import java.util.List;

public interface SuperDuperToDoService {
    public List<ToDoList> allToDoTasks();

    public ToDoList getToDoTask(int id) throws NotFoundException;

    public ToDoList createToDoTask(ToDoList toDoList);

    public ToDoList updateToDoTask(int id, ToDoList toDoList) throws NotFoundException;

    public void deleteToDoTask(int id) throws NotFoundException;
}
