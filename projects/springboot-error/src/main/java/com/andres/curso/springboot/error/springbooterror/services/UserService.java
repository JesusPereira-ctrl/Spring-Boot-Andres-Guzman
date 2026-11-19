package com.andres.curso.springboot.error.springbooterror.services;

import com.andres.curso.springboot.error.springbooterror.models.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);
}
