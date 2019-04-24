package com.comp680.backend.repositories;

import com.comp680.backend.models.Applicant;
import com.comp680.backend.models.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ApplicantsRepository extends CrudRepository<Applicant, Long> {

<<<<<<< HEAD
    // List<Applicant> findByUserId(long id);
    Applicant findById(long id);
    List<Applicant> findByUserId(long id);
=======
     List<Applicant> findByUserId(long id);
     List<User> findByUser(long id);
    boolean existsById(long id);
>>>>>>> applications
}
