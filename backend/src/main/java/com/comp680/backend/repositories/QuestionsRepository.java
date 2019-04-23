package com.comp680.backend.repositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.comp680.backend.models.Question;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {



    List<Question> findByJobId(Long id);

}