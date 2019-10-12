package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;




    //PUT
    //  http://localhost:2019/todos/todoid/{todoid}
    @PutMapping(value = "/todoid/{todoid}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@RequestBody Todo todo,
                                        @PathVariable long todoid)
    {
        todoService.update(todoid, todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
