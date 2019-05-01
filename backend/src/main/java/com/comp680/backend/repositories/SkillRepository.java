package com.comp680.backend.repositories;

import com.comp680.backend.models.Skill;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long> {

 
    Skill findById(long id);
    List<Skill> findByUserId(long id);
}
