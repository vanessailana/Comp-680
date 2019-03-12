package com.comp680.backend.repositories;

import com.comp680.backend.models.Social;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SocialsRepository extends CrudRepository<Social, Long> {

    Social findByUsersId(long id);
}



