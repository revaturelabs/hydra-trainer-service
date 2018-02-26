# hydra-trainer-service

The Trainer service handles CRUD operations and messaging for the trainer beans

Methods contained whitin this service:

•	TrainerCompositionService 
•	saveTrainer
•	updateTrainer
•	deleteTrainer
•	findTrainerById
•	findTrainerByEmail
•	findAll

TrainerCompositionMessagingService
•	sendSingleSimpleBatchRequest (finds all batches associated with a trainer)

TrainerRepositoryMessagingService 
•	receiveSingleSimpleTrainerRequest
•	receiveListSimpleTrainerRequest
•	receiveSingleTrainerRequest

TrainerRepositoryRequestDispatcher 
•	processSingleSimpleTrainerRequest
•	processListSimpleTrainerRequest
•	processSingleTrainerRequest
