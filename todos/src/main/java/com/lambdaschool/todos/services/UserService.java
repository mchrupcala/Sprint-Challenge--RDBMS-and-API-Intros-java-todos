package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService
{
    UserDetails loadUserByUsername(String username);

    List<User> findAll();

    List<User> findByNameContaining(String username);

    User findUserById(long id);

    User findByName(String name);

    void delete(long id);

    User save(User user);

//    User updateTodo(User user,
//                long id);

    User update(User user,
                long id,
                boolean isAdmin);

    void deleteUserRole(long userid,
                        long roleid);

    void addUserRole(long userid,
                     long roleid);
}