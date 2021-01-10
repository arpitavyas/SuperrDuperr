package com.deltaA.SuperrDuperr.repository;

import com.deltaA.SuperrDuperr.entity.ToDoListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ToDoListItemRepository extends JpaRepository<ToDoListItem, Integer> {

    @Query(value = "SELECT * from TBL_TODO_LIST_ITEM where list_id = :id",nativeQuery = true)
    List<ToDoListItem> findByListId (@Param("id") int id);

    @Query(value = "SELECT * from TBL_TODO_LIST_ITEM where item_status NOT LIKE 'Complete%'",nativeQuery = true)
    List<ToDoListItem> findByStatusCompleted();
}
