package com.deltaA.SuperrDuperr.repository;

import com.deltaA.SuperrDuperr.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {

}
