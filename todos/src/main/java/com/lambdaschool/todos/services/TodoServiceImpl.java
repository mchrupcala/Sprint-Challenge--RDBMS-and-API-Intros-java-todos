package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodoRepository;
import com.lambdaschool.todos.repository.UserRepository;
import com.lambdaschool.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todorepos;


    @Override
    public Todo save(Todo todo, long userid) {
        return null;
    }

    @Transactional
    @Override
    public Todo saveTodo(Todo todo, User user) {

        Todo newTodo = new Todo();

        newTodo.setDescription(todo.getDescription());
        newTodo.setDatestarted(todo.getDatestarted());
        newTodo.setCompleted(false);
        newTodo.setUser(user);

        //....but where do I send it to? which save method, and how do I connect the right user?
        return todorepos.save(newTodo);
//        return null;
    }

    @Override
    public Todo update(long id, Todo todo) {

        //model this off of UserServiceImpl...fix the null in Description

//        if (todo.getDescription() != null) {
            todo.setDescription(todo.getDescription());
//        }

//        if (todo.getDatestarted() != null) {
//
//        }

        //boolean?
        if (todo.isCompleted() || !todo.isCompleted()) {
            todo.setCompleted(todo.isCompleted());
        }

        if (todo.getUser() != null) {
            todo.setUser(todo.getUser());
        }

        return todorepos.save(todo);
    }
//    @Transactional
//    @Override
//    public Todo update
}
