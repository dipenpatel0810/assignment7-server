package com.example.assignment6server.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.assignment6server.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
