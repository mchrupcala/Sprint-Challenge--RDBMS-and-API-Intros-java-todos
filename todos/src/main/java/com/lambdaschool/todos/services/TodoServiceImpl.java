package main.java.com.lambdaschool.todos.services;

import main.java.com.lambdaschool.todos.models.Todo;
import main.java.com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todorepos;



//    @Transactional
//    @Override
//    public Todo save(Todo todo) {
//        Todo newTodo = new Todo();
//
//        newTodo.setCompleted(false);
//        newTodo.setDatestarted(todo.getDatestarted());
//        newTodo.setDescription(todo.getDescription());
//        newTodo.setUser(todo.getUser());
//
//        return todorepos.save(newTodo);
//    }
}
