package com.comp680.backend.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience, Long> {

    List<Experience> findByUsersId(long id);
    Experience findById(long id);
}

