package com.revature.hydra.trainer.model;

public class Certification {
	private String url;
	private String name;
	private int trainer;
	
	public Certification() {
		super();
	}

	public Certification(String url, String name, int trainer) {
		super();
		this.url = url;
		this.name = name;
		this.trainer = trainer;
	}
	
	// AssignForce has this pair of setter and getter as getFile
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTrainer() {
		return trainer;
	}

	public void setTrainer(int trainer) {
		this.trainer = trainer;
	}

	@Override
	public String toString() {
		return "Certification [File=" + url + ", name=" + name + ", trainer=" + trainer + "]";
	}
}
