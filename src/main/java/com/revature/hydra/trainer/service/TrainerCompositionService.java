package com.revature.hydra.trainer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Batch;
import com.revature.beans.SimpleBatch;
import com.revature.beans.SimpleTrainer;
import com.revature.beans.Trainer;
import com.revature.hydra.trainer.controller.TrainerController;
import com.revature.hydra.trainer.repository.TrainerRepository;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerCompositionService {

	@Autowired
	AmqpTemplate rabbitTemplate;

	@Autowired
	public TrainerRepository trainerRepository;

	@Autowired
	private TrainerCompositionMessagingService trainerCompositionMessagingService;
	
	private static final Logger log = Logger.getLogger(TrainerCompositionService.class);

	/**
	 * Save a SimpleTrainer
	 *
	 * @param trainer
	 *
	 * @return
	 */
	public void save(Trainer trainer) {
		SimpleTrainer simpleTrainer = new SimpleTrainer(trainer);
		if(trainer.getTrainerId() == 0) simpleTrainer.setTrainerId(null);
		trainerRepository.save(simpleTrainer);
	}

	/**
	 * Update a Trainer
	 *
	 * @param trainer
	 *
	 * @return
	 */
	public void update(Trainer trainer) {
		// save(trainer);
		log.trace("*****************");
		log.trace("Trainer submitted was: " + trainer);
		log.trace("*****************");
		trainerRepository.updateTrainerInfoById(trainer.getName(), trainer.getTitle(), trainer.getTier(), trainer.getTrainerId());
		
	}

	/**
	 * Delete a single Trainer
	 *
	 * @param trainer
	 *
	 * @return
	 */
	public void delete(Trainer trainer) {
		trainerRepository.delete(trainer.getTrainerId());
	}

	/**
	 * Find a single Trainer by trainerId
	 *
	 * @param trainerId
	 *
	 * @return Trainer
	 */
	public Trainer findById(Integer trainerId) {
		log.trace("******************");
		log.trace("Trainer Id: " + trainerId);
		log.trace("******************");
		SimpleTrainer basis = trainerRepository.findOne(trainerId);
		Trainer result = composeTrainer(basis);

		log.trace("Trainer found: " + result);
		return result;
	}
	
	/**
	 * Find a single Trainer by name
	 *
	 * @param name
	 *
	 * @return Trainer
	 */
	// TODO: Need to confirm
	public Trainer findByName(String name) {
		log.trace("*****************");
		log.trace("Name to find: " + name);
		log.trace("*****************");
		SimpleTrainer basis = trainerRepository.findByName(name);
		Trainer result = composeTrainer(basis);

		log.trace("Trainer found: " + result);
		return result;
	}
	

	/**
	 * Find a single Trainer by email
	 *
	 * @param trainerId
	 *
	 * @return Trainer
	 */
	public Trainer findByEmail(String email) {
		log.trace("*******************");
		log.trace("Email to find: " + email);
		log.trace("*******************");
		SimpleTrainer basis = trainerRepository.findByEmail(email);
		Trainer result = composeTrainer(basis);
		
		log.trace("Trainer found: " + result);
		return result;
	}

	/**
	 * Find all Trainers
	 *
	 * @param
	 *
	 * @return List of Trainers
	 */
	public List<Trainer> findAll() {
		List<Trainer> result = new ArrayList<Trainer>();
		List<SimpleTrainer> basis = trainerRepository.findAll();
		for (SimpleTrainer t : basis) {
			result.add(composeTrainer(t));
		}

		return result;
	}

	/**
	 * convert a SimpleTrainer in to a Trainer Trainer has a set of batches
	 * associated with it SimpleTrainer does not
	 *
	 * @param src
	 *
	 * @return Trainer
	 */
	private Trainer composeTrainer(SimpleTrainer src) {
		
		List<SimpleBatch> batchSet = trainerCompositionMessagingService
				.sendSingleSimpleBatchRequest((Integer) src.getTrainerId());

		Trainer dest = new Trainer(src);

		dest.setBatches(batchSet.stream().map(x -> new Batch(x)).collect(Collectors.toSet()));

		return dest;
		
		// For testing purposes, setting batches to null ...
		/*
		log.trace("****************************");
		log.trace("Simple Trainer was: " + src);
		log.trace("****************************");

		Trainer dest = new Trainer(src);		
		dest.setBatches(null);
		return dest;
		*/
	}

}