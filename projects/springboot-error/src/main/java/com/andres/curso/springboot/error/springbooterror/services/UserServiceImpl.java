package com.andres.curso.springboot.error.springbooterror.services;

import com.andres.curso.springboot.error.springbooterror.models.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users;

    public UserServiceImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(usr -> usr.getId().equals(id))
                .findFirst();
    }
}
