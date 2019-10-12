package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;

public interface TodoService {
    Todo save(Todo todo, long userid);

    Todo saveTodo(Todo todo, User user);

    Todo update(long id, Todo todo);
}
