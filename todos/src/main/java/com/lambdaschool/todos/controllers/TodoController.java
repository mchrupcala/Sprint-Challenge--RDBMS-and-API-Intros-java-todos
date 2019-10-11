package main.java.com.lambdaschool.todos.controllers;

import main.java.com.lambdaschool.todos.services.TodoService;

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
