package com.damvinod.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	private static int usersCount = 3;
	
	static{
		
		users.add(new User(1, "Vinod", new Date()));
		users.add(new User(2, "Sindhuja", new Date()));
		users.add(new User(3, "Vindhya", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user){
		if(user.getId() == null){
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public boolean delete(int id){
		return users.removeIf((User user) -> user.getId() == id);
	}
	
	public User findOne(int id)
	{
		return users.stream().filter((User user) -> user.getId() == id).findAny().orElse(null);
	}
}

