package com.comp680.backend.repositories;

import java.util.List;
import com.comp680.backend.models.User;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}

