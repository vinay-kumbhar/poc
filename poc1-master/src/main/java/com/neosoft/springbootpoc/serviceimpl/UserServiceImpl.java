package com.neosoft.springbootpoc.serviceimpl;

import java.util.List;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.springbootpoc.dao.UserDao;
import com.neosoft.springbootpoc.entity.User;
import com.neosoft.springbootpoc.exception.InvalidId;
import com.neosoft.springbootpoc.exception.InvalidUser;
import com.neosoft.springbootpoc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;

	@Override
	public String userRegistration(User user) throws InvalidUser {
		if (user == null || user.getFirstName() == null || user.getPincode() == null || user.getSurName() == null) {
			throw new InvalidUser("user should not be null");
		} else {

			user.setDeleted(false);
			userdao.save(user);
			return "user registerd successfully!!";
		}
	}

	@Override
	public void updateUser(int id, User user) {

	}

	@Override
	public User findUserBy(String firstName, String lastname) {

		User findUserBy = userdao.findUserBy(firstName, lastname);
		System.out.println(findUserBy);
		return findUserBy;
	}

	@Override
	public User fetchUser(String firstname) {
		return userdao.fethcUserByName(firstname);
	}

	@Override
	public List<User> findyBy(String firstname, String surname, Long pincode) throws InvalidNameException {
		if (firstname == null && surname == null && pincode == null) {
			throw new InvalidNameException("should not be null");
		} else {
			return userdao.fetchBy(firstname, surname, pincode);
		}
	}

	@Override
	public String updateUser(User user, int userid) {

		User user1 = userdao.findById(userid).get();
		user1.setCity(user.getCity());
		user1.setPincode(user.getPincode());
		user1.setAge(user.getAge());
		user1.setEmail(user.getEmail());
		user1.setFirstName(user.getFirstName());
		user1.setOccupation(user.getOccupation());
		user1.setState(user.getState());
		user1.setSurName(user.getSurName());
		user1.setCountry(user.getCountry());
		user1.setDob(user.getDob());
		user1.setDoj(user.getDoj());
		userdao.save(user1);
		return "updated";

	}

	@Override
	public String removeUser(int userId) {
		if (userId == 0) {
			throw new InvalidId("id should not be null");
		} else {
			userdao.deleteById(userId);
			return "user is deleted";
		}
	}

	@Override
	public List<User> fetchUserByDob() {
		return userdao.fetchUsersByDob();
	}

	@Override
	public int softDelete(int id) {
		if (id == 0) {
			throw new InvalidId("id should not be null");
		} else {

			userdao.softDelete(id);
			return 1;
		}

	}

	@Override
	public List<User> fetchUserByDoj() {

		return userdao.fetchByDoj();
	}

	@Override
	public User fetchUserById(int id) {

		if (id == 0) {
			throw new InvalidId("id should not be null");
		} else {
			return userdao.findById(id).get();

		}

	}
}
