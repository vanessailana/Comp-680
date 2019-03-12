package com.comp680.backend.repositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import com.comp680.backend.models.Job;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface JobRepository extends JpaRepository<Job, Long> {

}