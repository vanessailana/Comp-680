package com.comp680.backend.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SocialRepository extends CrudRepository<Social, Long> {

    Social findByUsersId(long id);
}



