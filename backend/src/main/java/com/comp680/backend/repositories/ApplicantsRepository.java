package com.comp680.backend.repositories;

import com.comp680.backend.models.Applicant;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ApplicantsRepository extends CrudRepository<Applicant, Long> {

    // List<Applicant> findByUserId(long id);
    Applicant findById(long id);
}
