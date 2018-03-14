package com.revature.hydra.trainer;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.SimpleTrainer;
import com.revature.beans.TrainerRole;
import com.revature.hydra.trainer.data.TrainerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainerRepositoryTests {
	
	@Autowired
	TrainerRepository test;
	
	@Test
	public void TestfindAll() {
		List<SimpleTrainer> trainer = test.findAll();
		assertNotNull(trainer);
	}
	
	@Test
	public void TestfindByTrainerId() {
		SimpleTrainer trainer = test.findByTrainerId(1);
		assertNotNull(trainer);
	}
	
	@Test
	public void TestfindAllTrainerTitles() {
		List<String> trainer = test.findAllTrainerTitles();
		assertNotNull(trainer);
	}
	
	@Test
	public void TestfindByEmail() {
		SimpleTrainer trainer = test.findByEmail("pjw6193@hotmail.com");
		assertNotNull(trainer);
	}
	
	@Test
	public void TestupdateTrainerInfoById() {
		test.updateTrainerInfoById("test", "test", TrainerRole.ROLE_INACTIVE, "resume1", (Integer) 1);
		assertNotNull(test.findByTrainerId(1));
	}
}
