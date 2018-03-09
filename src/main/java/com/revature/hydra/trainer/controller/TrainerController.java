package com.revature.hydra.trainer.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hydra.trainer.model.SimpleTrainer;
import com.revature.hydra.trainer.model.Trainer;
import com.revature.hydra.trainer.model.TrainerRole;
import com.revature.hydra.trainer.repository.TrainerRepository;
import com.revature.hydra.trainer.service.TrainerCompositionService;

@RestController
@CrossOrigin
public class TrainerController {

	private static final Logger log = Logger.getLogger(TrainerController.class);

	@Autowired
	private TrainerCompositionService trainerCompositionService;

	/**
	 * Create trainer
	 *
	 * @param trainer
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/vp/trainer/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Trainer> createTrainer(@Valid @RequestBody Trainer trainer) {
		log.info("Saving trainer: " + trainer);
		trainerCompositionService.save(trainer);
		return new ResponseEntity<>(trainer, HttpStatus.CREATED);
	}

	/**
	 * Update trainer
	 *
	 * @param trainer
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/vp/trainer/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Void> updateTrainer(@Valid @RequestBody Trainer trainer) {
		log.info("Updating trainer: " + trainer);
		trainerCompositionService.update(trainer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Finds a trainer by email. Used for logging in a user with the Salesforce
	 * controller `
	 *
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/training/trainer/byemail/{email}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("permitAll")
	public ResponseEntity<Trainer> findTrainerByEmail(@PathVariable String email) {
		log.trace("Find trainer by email " + email);
		Trainer trainer = trainerCompositionService.findByEmail(email);
		return new ResponseEntity<>(trainer, HttpStatus.OK);
	}

	/**
	 * Deactivates the trainer
	 *
	 * @param trainer
	 * @return response entity
	 */
	// AssignForce deletes based on Id
	@RequestMapping(value = "/vp/trainer/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Void> makeInactive(@Valid @RequestBody Trainer trainer) {
		log.info("Updating trainer: " + trainer);
		trainer.setTier(TrainerRole.ROLE_INACTIVE);
		trainerCompositionService.update(trainer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Returns all trainers titles from the database `
	 *
	 * @return
	 */
	@RequestMapping(value = "/vp/trainer/titles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('VP', 'TRAINER', 'STAGING', 'QC', 'PANEL')")
	public ResponseEntity<List<String>> getAllTrainersTitles() {
		log.info("Fetching all trainers titles");
		List<String> trainers = trainerCompositionService.trainerRepository.findAllTrainerTitles();
		return new ResponseEntity<>(trainers, HttpStatus.OK);
	}

	/**
	 * Returns all trainers from the database `
	 *
	 * @return
	 */
	@RequestMapping(value = "/all/trainer/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	// @PreAuthorize("hasAnyRole('VP', 'TRAINER', 'STAGING', 'QC', 'PANEL')")
	public ResponseEntity<List<Trainer>> getAllTrainers() {
		log.info("Fetching all trainers");
		List<Trainer> trainers = trainerCompositionService.findAll();
		return new ResponseEntity<>(trainers, HttpStatus.OK);
	}
	
	// AssignForce
	/**
	 * Returns a trainer by their id.
	 * 
	 * @param id
	 * @return Trainer
	 */
	@RequestMapping(value = "/vp/trainer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Trainer> findTrainerById(@PathVariable("id") Integer id){
		log.info("Fetching trainer base on id.");
		return new ResponseEntity<>(trainerCompositionService.findById(id), HttpStatus.FOUND);
	}
	
	/**
	 * Returns trainer by their name
	 * 
	 * @param firstName, lastName
	 * @return Trainer
	 */
	@RequestMapping(value = "/vp/trainer/{firstName}/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trainer> findByName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		String name = firstName + " " + lastName;
		return new ResponseEntity<>(trainerCompositionService.findByName(name), HttpStatus.FOUND);
	}

}
