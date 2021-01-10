package com.deltaA.SuperrDuperr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBL_TODO_LIST_ITEM")
public class ToDoListItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="item_id")
    private int item_id;

    @Column(name="item_name")
    private String itemName;

    @Column(name="item_status")
    private String itemStatus;

    @Column(name="item_due_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date itemDueDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ToDoList toDoList;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "TBL_TAG_ITEMS",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tags> tags = new HashSet<>();

    public ToDoListItem() {

    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public ToDoListItem(String itemName, String itemStatus, Date itemDueDate, ToDoList toDoList) {
        this.itemName = itemName;
        this.itemStatus = itemStatus;
        this.itemDueDate = itemDueDate;
        this.toDoList = toDoList;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Date getItemDueDate() {
        return itemDueDate;
    }

    public void setItemDueDate(Date itemDueDate) {
        this.itemDueDate = itemDueDate;
    }


    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ToDoListItem{" +
                "item_id=" + item_id +
                ", itemName='" + itemName + '\'' +
                ", itemStatus='" + itemStatus + '\'' +
                ", itemDueDate=" + itemDueDate +
                '}';
    }
}
