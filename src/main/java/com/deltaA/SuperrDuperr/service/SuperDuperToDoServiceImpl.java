package com.deltaA.SuperrDuperr.service;

import com.deltaA.SuperrDuperr.entity.ToDoList;
import com.deltaA.SuperrDuperr.exception.NotFoundException;
import com.deltaA.SuperrDuperr.repository.ToDoListItemRepository;
import com.deltaA.SuperrDuperr.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SuperDuperToDoServiceImpl implements SuperDuperToDoService {
    @Autowired
    ToDoListRepository toDoListRepository;

    @Autowired
    ToDoListItemRepository toDoListItemRepository;

    @Override
    public List<ToDoList> allToDoTasks() {
        return toDoListRepository.findAll();
    }

    @Override
    public ToDoList getToDoTask(int id) throws NotFoundException {
        Optional<ToDoList> toDoList =  toDoListRepository.findById(id);
        if(toDoList.isPresent()) {
            return toDoList.get();
        }
        throw new NotFoundException("To task not present : "+ id);
    }

    @Override
    public ToDoList createToDoTask(ToDoList toDoList) {
        return toDoListRepository.save(toDoList);
    }

    @Override
    public ToDoList updateToDoTask(int id, ToDoList toDoListRequest) throws NotFoundException {
        Optional<ToDoList> _todo = toDoListRepository.findById(id);
        if (_todo.isPresent()) {
            _todo.get().setName(toDoListRequest.getName());
            return toDoListRepository.save(_todo.get());
        }
        throw new NotFoundException("To task not present : "+ id);
    }

    @Override
    public void deleteToDoTask(int id) throws NotFoundException {
        Optional<ToDoList> _todo = toDoListRepository.findById(id);
        if (_todo.isPresent()) {
            toDoListRepository.delete(_todo.get());
        }
    }
}
