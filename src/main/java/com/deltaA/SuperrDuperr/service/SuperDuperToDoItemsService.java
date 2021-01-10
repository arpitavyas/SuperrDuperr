package com.deltaA.SuperrDuperr.service;

import com.deltaA.SuperrDuperr.entity.Tags;
import com.deltaA.SuperrDuperr.entity.ToDoList;
import com.deltaA.SuperrDuperr.entity.ToDoListItem;
import com.deltaA.SuperrDuperr.exception.NotFoundException;

import java.util.List;

public interface SuperDuperToDoItemsService {

    public List<ToDoListItem> allToDoTaskItems(int taskId);

    public ToDoListItem getToDoTaskItem(int taskId, int itemId);

    public ToDoListItem createToDoTaskItem(int taskId, ToDoListItem toDoListItem) throws NotFoundException;

    public ToDoListItem updateToDoTaskItem(int taskId, int itemId, ToDoListItem toDoListItem) throws NotFoundException;

    public ToDoListItem addTagToDoTaskItem(int taskId, int itemId, Tags toDoListItem) throws NotFoundException;

    public void deleteToDoTaskItem(int taskId, int itemId) throws NotFoundException;

}
