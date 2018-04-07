package com.revature.hydra.trainer.repo;

import com.revature.hydra.trainer.model.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.annotations.Api;

@Repository
@Api
public interface TrainerRepo extends JpaRepository<Trainer, Integer> {
}