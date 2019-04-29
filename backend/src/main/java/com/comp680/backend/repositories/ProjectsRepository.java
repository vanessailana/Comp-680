package com.comp680.backend.repositories;

import com.comp680.backend.models.Project;
import com.comp680.backend.models.Skill;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProjectsRepository extends CrudRepository<Project, Long> {

    Project findById(long id);
}
