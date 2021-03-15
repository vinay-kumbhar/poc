package com.neosoft.springbootpoc.service;


import java.util.List;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Service;

import com.neosoft.springbootpoc.entity.User;
import com.neosoft.springbootpoc.exception.InvalidUser;
public interface UserService {

	public String userRegistration(User user) throws InvalidUser;

	public void updateUser(int id, User user);

	public User findUserBy(String firstName, String lastname)throws InvalidNameException;

	public User fetchUser(String firstname);

	public List<User> findyBy(String firstname, String surname, Long pincode)throws InvalidNameException;

	public String updateUser(User user, int userid);

	public String removeUser(int userId);

	public List<User> fetchUserByDob();

	public int softDelete(int id);

	public List<User> fetchUserByDoj();

	public User fetchUserById(int id);



	
	
}
