package main.java.com.lambdaschool.todos.services;

import main.java.com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todorepos;
}
