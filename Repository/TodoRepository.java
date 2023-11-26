package com.example.todo.Repository;

import com.example.todo.Entity.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {

    @Query("select t from Todo j where t.is_completed = false and t.is_postponed = false order by t.is_active desc")
    List<Todo> findByCompletedAndPostponed();
    @Query("select t from Todo t where t.is_postponed = true")
    List<Todo> findByPostponed();
    @Query("select t from Todo t where t.is_completed = true")
    List<Todo> findByCompleted();
    @Query("select t from Todo t order by t.is_active desc, t.is_completed asc, t.is_postponed asc")
    List<Todo> orderByToDo();


}
