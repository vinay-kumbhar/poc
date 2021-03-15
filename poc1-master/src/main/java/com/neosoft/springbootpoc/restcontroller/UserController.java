package com.neosoft.springbootpoc.restcontroller;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.springbootpoc.entity.User;
import com.neosoft.springbootpoc.exception.InvalidId;
import com.neosoft.springbootpoc.exception.InvalidName;
import com.neosoft.springbootpoc.exception.InvalidUser;
import com.neosoft.springbootpoc.response.DataResponse;
import com.neosoft.springbootpoc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice;

	@PostMapping(path = "/registeruser")
	public DataResponse registerUser(@Valid @RequestBody User user) {

		if (user.getFirstName() == null || user.getPincode() == null || user.getSurName() == null) {
			throw new InvalidUser("mandatory field should not be empty");
		} else {
			DataResponse dataResponse = new DataResponse();
			String message = userservice.userRegistration(user);
			dataResponse.setMessage(message);
			dataResponse.setStatus(HttpStatus.OK);
			return dataResponse;

		}
	}

	@GetMapping("/fetch")
	public DataResponse fetchBy(@RequestParam(value = "firstName", required = false) String firstname,
			@RequestParam(value = "surName", required = false) String surname,
			@RequestParam(value = "pincode", required = false) Long pincode) throws InvalidNameException {

		if (firstname == null && surname == null && pincode == null) {
			throw new InvalidName("name should not be null");

		} else {

			DataResponse dataResponse = new DataResponse();
			List<User> userlist = userservice.findyBy(firstname, surname, pincode);
			dataResponse.setBody(userlist);
			dataResponse.setMessage("this is the  list of user's");
			dataResponse.setStatus(HttpStatus.OK);
			return dataResponse;
		}
	}

	@PutMapping(path = "/update/{id}")
	public DataResponse updateUser(@RequestBody User user, @PathVariable("id") int userid) {
		if (userid == 0) {
			throw new InvalidId("id should not be null");
		} else {
			DataResponse dataResponse=new DataResponse();
			String message=userservice.updateUser(user, userid);
			dataResponse.setMessage(message);
			return dataResponse;
		}
	}

	@DeleteMapping(path = "/remove/hard/{userId}")
	public DataResponse deleteUser(@PathVariable("userId") int userId) {
		System.out.println(userId);
		if (userId == 0) {
			throw new InvalidId("Id should not be Null");
		} else {
			String message = userservice.removeUser(userId);
			DataResponse dataResponse = new DataResponse();
			dataResponse.setMessage(message);
			dataResponse.setStatus(HttpStatus.OK);
			return dataResponse;
		}

	}

	@GetMapping("/fetchuserbydob")
	public DataResponse sortUserByDob() {
		List<User> userlist = userservice.fetchUserByDob();
		DataResponse dataResponse = new DataResponse();
		dataResponse.setBody(userlist);
		return dataResponse;
	}

	@GetMapping("/fetchuserbydoj")
	public DataResponse sortUserByDoj() {
		List<User> userlist = userservice.fetchUserByDoj();
		DataResponse dataResponse = new DataResponse();
		dataResponse.setBody(userlist);
		return dataResponse;
	}

	@DeleteMapping("/soft/remove/{id}")
	public int softDelete(@PathVariable("id") int id) {
		if (id == 0) {
			throw new InvalidId("id should not be null");
		} else {
			int message = userservice.softDelete(id);
			return message;
		}
	}

	@GetMapping("/fetchuserbyid/{id}")
	public DataResponse fetchuserById(@PathVariable("id") int id) {

		if (id == 0) {
			throw new InvalidId("id should not be null");
		} else {
			User user = userservice.fetchUserById(id);
			DataResponse dataResponse = new DataResponse();
			dataResponse.setBody(user);
			dataResponse.setStatus(HttpStatus.OK);
			return dataResponse;
		}

	}
}