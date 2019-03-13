package com.comp680.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.comp680.backend.models.Job;
import org.springframework.stereotype.Repository;


@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {

    List<Job> findByUserId(Long id);

}