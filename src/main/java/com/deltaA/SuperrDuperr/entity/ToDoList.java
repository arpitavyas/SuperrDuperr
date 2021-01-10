package com.deltaA.SuperrDuperr.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "TBL_TODO_LISTS")
public class ToDoList {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int listId;

    @Column(name="name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "toDoList")
    @JsonIgnore
    private List<ToDoListItem> toDoListItems;

    public ToDoList() {
    }

    public ToDoList(String name) {
        this.name = name;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "id=" + listId +
                ", name='" + name + '\'' +
                '}';
    }
}
