package com.comp680.backend.repositories;

import com.comp680.backend.models.Applicant;
import com.comp680.backend.models.Job;
import com.comp680.backend.models.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ApplicantsRepository extends CrudRepository<Applicant, Long> {

     List<Applicant> findByUserId(long id);
 
    boolean existsById(long id);
}
