package com.comp680.backend.repositories;

import com.comp680.backend.models.Experience;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExperiencesRepository extends CrudRepository<Experience, Long> {

    List<Experience> findByUsersId(long id);
    Experience findById(long id);
}

