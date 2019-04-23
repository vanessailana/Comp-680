package com.comp680.backend.repositories;

import com.comp680.backend.models.Answer;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

    Answer findById(long id);
    
}
