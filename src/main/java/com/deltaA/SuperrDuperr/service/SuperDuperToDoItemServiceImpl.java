package com.deltaA.SuperrDuperr.service;

import com.deltaA.SuperrDuperr.entity.Tags;
import com.deltaA.SuperrDuperr.entity.ToDoList;
import com.deltaA.SuperrDuperr.entity.ToDoListItem;
import com.deltaA.SuperrDuperr.exception.NotFoundException;
import com.deltaA.SuperrDuperr.repository.ToDoListItemRepository;
import com.deltaA.SuperrDuperr.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SuperDuperToDoItemServiceImpl implements SuperDuperToDoItemsService {

    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    ToDoListItemRepository toDoListItemRepository;

    @Override
    public List<ToDoListItem> allToDoTaskItems(int taskId) {
        return toDoListItemRepository.findByListId(taskId);
    }

    @Override
    public ToDoListItem getToDoTaskItem(int taskId, int itemId) {
        return toDoListItemRepository.findById(itemId).get();
    }

    @Override
    public ToDoListItem createToDoTaskItem(int taskId, ToDoListItem toDoListItem) throws NotFoundException {
        Optional<ToDoList> todo = toDoListRepository.findById(taskId);
        if (todo.isPresent()) {
            toDoListItem.setToDoList(todo.get());
            return toDoListItemRepository.save(toDoListItem);
        }
        throw new NotFoundException("ToDo task not present : "+ taskId);
    }

    @Override
    public ToDoListItem updateToDoTaskItem(int taskId, int itemId, ToDoListItem toDoListItem) throws NotFoundException {
        Optional<ToDoList> _todo = toDoListRepository.findById(taskId);
        if (_todo.isPresent()) {
            Optional<ToDoListItem> _todoItem = toDoListItemRepository.findById(itemId);
            if (_todoItem.isPresent()) {
                _todoItem.get().setItemName(toDoListItem.getItemName());
                _todoItem.get().setItemDueDate(toDoListItem.getItemDueDate());
                _todoItem.get().setItemStatus(toDoListItem.getItemStatus());
                return toDoListItemRepository.save(_todoItem.get());
            }
        }
        throw new NotFoundException("ToDo task not present : "+ taskId);
    }

    @Override
    public ToDoListItem addTagToDoTaskItem(int taskId, int itemId, Tags tag) throws NotFoundException {
        Optional<ToDoList> todo = toDoListRepository.findById(taskId);
        if (todo.isPresent()) {
            Optional<ToDoListItem> _todoList = toDoListItemRepository.findById(itemId);
            if (_todoList.isPresent()) {
                _todoList.get().getTags().add(tag);
                return toDoListItemRepository.save(_todoList.get());
            }
        }
        throw new NotFoundException("ToDo task not present : "+ taskId);
    }

    @Override
    public void deleteToDoTaskItem(int taskId, int itemId) throws NotFoundException {
        Optional<ToDoList> _todo = toDoListRepository.findById(taskId);
        if (_todo.isPresent()) {
            Optional<ToDoListItem> _todoItem = toDoListItemRepository.findById(itemId);
            if (_todoItem.isPresent()) {
                toDoListItemRepository.delete(_todoItem.get());
            } else {
                throw new NotFoundException("ToDo task not present : "+ taskId);
            }
        }
    }
}
