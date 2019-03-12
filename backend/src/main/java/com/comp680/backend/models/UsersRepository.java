package com.comp680.backend.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {

    Users findByEmail(String email);

    Users findById(long id);
}

