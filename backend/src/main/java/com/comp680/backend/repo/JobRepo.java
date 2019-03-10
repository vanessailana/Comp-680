package com.comp680.backend.repo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import com.comp680.backend.models.Jobs;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface JobRepo extends JpaRepository<Jobs, Long> {

}