package com.comp680.backend.repositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD:backend/src/main/java/com/comp680/backend/repositories/QuestionsRepository.java
import com.comp680.backend.models.Job;
=======
import com.comp680.backend.models.Questions;
>>>>>>> CRUD_Job_Posting_Backend:backend/src/main/java/com/comp680/backend/repo/QuestionRepo.java
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository


<<<<<<< HEAD:backend/src/main/java/com/comp680/backend/repositories/QuestionsRepository.java
public interface QuestionsRepository extends JpaRepository<Job, Long> {
=======
public interface QuestionRepo extends JpaRepository<Questions, Long> {
>>>>>>> CRUD_Job_Posting_Backend:backend/src/main/java/com/comp680/backend/repo/QuestionRepo.java

}