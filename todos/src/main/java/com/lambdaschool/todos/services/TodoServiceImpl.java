package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.repository.TodoRepository;
import com.lambdaschool.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todorepos;



    @Transactional
    @Override
    public Todo save(Todo todo) {
        Todo newTodo = new Todo();

        newTodo.setDescription(todo.getDescription());
        newTodo.setDatestarted(todo.getDatestarted());
//        newTodo.setCompleted(false);
        newTodo.setUser(todo.getUser());

        return todorepos.save(newTodo);
    }
}
