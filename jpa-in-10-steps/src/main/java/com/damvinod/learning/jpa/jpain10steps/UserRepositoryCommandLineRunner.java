package com.damvinod.learning.jpa.jpain10steps;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.damvinod.learning.jpa.jpain10steps.entity.User;
import com.damvinod.learning.jpa.jpain10steps.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		User user = new User("Jill", "Admin");
		userRepository.save(user);
		
		logger.info("New user is created by UserRepository " + user);
		
		List<User> users = userRepository.findAll();
		
		users.forEach((User userTemp) -> logger.info("All Users " + userTemp));
	}

}
