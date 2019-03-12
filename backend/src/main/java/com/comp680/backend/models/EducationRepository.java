package com.comp680.backend.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education, Long> {

    List<Education> findByUsersId(long id);
    Education findById(long id);
}

