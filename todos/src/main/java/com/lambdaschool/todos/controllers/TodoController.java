package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;




    //PUT
    //  http://localhost:2019/todos/todoid/{todoid}
//    @GetMapping(value = "/todoid/{todoid}",
//                produces = {"application/json"})
//    public ResponseEntity<?>


}
