package com.revature.hydra.trainer.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InterviewFormat implements Serializable{

	@JsonProperty("Skype")
	Skype,
	@JsonProperty("Phone")
	Phone
	
}
