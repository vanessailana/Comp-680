package com.comp680.backend.repositories;

import com.comp680.backend.models.Education;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EducationsRepository extends CrudRepository<Education, Long> {

  
    Education findById(long id);
    List<Education> findByUserId(long id);
}
