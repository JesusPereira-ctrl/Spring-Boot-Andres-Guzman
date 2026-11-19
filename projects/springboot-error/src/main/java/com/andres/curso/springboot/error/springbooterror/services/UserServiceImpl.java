package com.andres.curso.springboot.error.springbooterror.services;

import com.andres.curso.springboot.error.springbooterror.models.domain.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users;

    public UserServiceImpl() {
        this.users = Arrays.asList(
                new User(1L, "Pepe", "Gonzalez"),
                new User(2L, "Andres", "Mena"),
                new User(3L, "Maria", "Perez"),
                new User(4L, "Josefa", "Ramirez"),
                new User(5L, "Ale", "Gutierrez")
        );
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = null;

        for (User u : users) {
            if (u.getId().equals(id)) {
                user = u;
                break;
            }
        }

        return user;
    }
}
