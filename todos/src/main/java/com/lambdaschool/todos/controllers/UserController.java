package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.services.TodoService;
import com.lambdaschool.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    //GET
    //  http://localhost:2019/users/mine
    @GetMapping(value = "/mine",
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getMyUser(Authentication authentication)
    {
        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }




    //POST    IT WORKS! (I THINK?)
    //  http://localhost:2019/users/user

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid
                                        @RequestBody
                                                User newuser) throws URISyntaxException
    {
        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }


    //POST
    //  http://localhost:2019/users/todo/{userid}

    @PostMapping(value = "/todo/{userid}",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewTodo(@RequestBody
                                                Todo newtodo,
                                        @PathVariable long userid)
    {
        User u = userService.findUserById(userid);
        //why is my save method from User expecting a long?
        todoService.saveTodo(newtodo, u);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }













    //DELETE
    //  http://localhost:2019/users/userid/{id}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/userid/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    // http://localhost:2019/users/users/
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
                HttpStatus.OK);
    }

    // http://localhost:2019/users/user/7
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userId}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserById(
            @PathVariable
                    Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // http://localhost:2019/users/user/name/cinnamon
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserByName(
            @PathVariable
                    String userName)
    {
        User u = userService.findByName(userName);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // http://localhost:2019/users/user/name/like/da?sort=username
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/like/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> getUserLikeName(
            @PathVariable
                    String userName)
    {
        List<User> u = userService.findByNameContaining(userName);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }


    // http://localhost:2019/users/getusername
    @GetMapping(value = "/getusername",
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(Authentication authentication)
    {
        return new ResponseEntity<>(authentication.getPrincipal(),
                HttpStatus.OK);
    }



    // http://localhost:2019/users/getuserinfo
    @GetMapping(value = "/getuserinfo",
            produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication)
    {
        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // http://localhost:2019/users/user
    //        {
    //            "username": "Mojo",
    //            "primaryemail": "mojo@lambdaschool.local",
    //            "password" : "Coffee123",
    //            "useremails": [
    //            {
    //                "useremail": "mojo@mymail.local"
    //            },
    //            {
    //                "useremail": "mojo@mymail.local"
    //            },
    //            {
    //                "useremail": "mojo@email.local"
    //            }
    //        ]
    //        }



    // http://localhost:2019/users/user/7
    //        {
    //            "userid": 7,
    //                "username": "cinnamon",
    //                "primaryemail": "cinnamon@lambdaschool.home",
    //                "useremails": [
    //            {
    //                    "useremail": "cinnamon@mymail.local"
    //            },
    //            {
    //                    "useremail": "hops@mymail.local"
    //            },
    //            {
    //                    "useremail": "bunny@email.local"
    //            }
    //        ]
    //        }
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long id)
    {
        userService.update(updateUser,
                id,
                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }




    // http://localhost:2019/users/user/15/role/2
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> deleteUserRoleByIds(
            @PathVariable
                    long userid,
            @PathVariable
                    long roleid)
    {
        userService.deleteUserRole(userid,
                roleid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // http://localhost:2019/users/user/15/role/2
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> postUserRoleByIds(
            @PathVariable
                    long userid,
            @PathVariable
                    long roleid)
    {
        userService.addUserRole(userid,
                roleid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}