package com.comp680.backend.repositories;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.comp680.backend.models.Job;
import org.springframework.stereotype.Repository;
@Repository
public interface JobsRepository extends CrudRepository<Job, Long> {

    List<Job> findByUserId(long userId);
    boolean existsById(long id);
    Job findByIdAndUserId(long id, long user_id);

}