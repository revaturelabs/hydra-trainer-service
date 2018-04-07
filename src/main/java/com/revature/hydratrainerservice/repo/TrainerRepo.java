package com.revature.hydratrainerservice.repo;

import com.revature.hydratrainerservice.model.Tier;
import com.revature.hydratrainerservice.model.Trainer;
import io.swagger.annotations.Api;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Api
public interface TrainerRepo extends CrudRepository<Trainer, Integer> {
    Set<Trainer> findByTier(@Param("tier") Tier tier);
}