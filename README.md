# hydra-trainer-service

The Trainer service handles CRUD operations and messaging for the trainer beans.

Location of Trainer Related Beans:

	com/revature/beans

Trainer Specific Beans:

	Trainer

	SimpleTrainer

	TrainerRole

	Certification

Methods contained within this service:

**TrainerController - Found in com/revature/hydra/trainer/controller/**
The TrainerController is where the Trainer service end points are defined.

End Points
* /vp/trainer/create
	Taking in a Trainer object within the RequestBody, create and store a new Trainer
* /vp/trainer/{id}
	Using an Integer Id value from the mapping, return the corresponding Trainer
* /vp/trainer/{firstName}/{lastName}
	Using a given first name and last name taken from the mapping, return the corresponding Trainer
* /training/trainer/byemail/{email}/
	Using a given email taken from the mapping, return the corresponding mapping
* /all/trainer/all
	Retrieving all non-inactive Trainers from the database
* /vp/trainer/titles
	Retrieving all the Trainer titles, which is defined as the Trainer's role within the company
	Example: Lead Trainer, Senior Trainer, Staging Manager
* /vp/trainer/update
	Given a Trainer object within the RequestBody, update the Trainer information within the database
* /vp/trainer/delete
	Given a Trainer object within the RequestBody, set the Trainer as inactive

TrainerCompositionService 

	saveTrainer
	
	updateTrainer
	
	deleteTrainer
	
	findTrainerById
	
	findTrainerByEmail
	
	findAll

TrainerCompositionMessagingService

	sendSingleSimpleBatchRequest (finds all batches associated with a trainer)
	
	TrainerRepositoryMessagingService 
	
	receiveSingleSimpleTrainerRequest
	

ReceiveListSimpleTrainerRequest

	receiveSingleTrainerRequest
	

TrainerRepositoryRequestDispatcher 

	processSingleSimpleTrainerRequest
	
	processListSimpleTrainerRequest
	
	processSingleTrainerRequest
	

