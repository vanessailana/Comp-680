package com.comp680.backend.repositories;

import java.util.List;
import com.comp680.backend.models.Users;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {

    Users findByEmail(String email);
}

