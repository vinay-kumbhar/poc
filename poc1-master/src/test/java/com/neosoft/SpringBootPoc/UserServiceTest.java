package com.neosoft.SpringBootPoc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.neosoft.springbootpoc.dao.UserDao;
import com.neosoft.springbootpoc.entity.User;
import com.neosoft.springbootpoc.exception.InvalidId;
import com.neosoft.springbootpoc.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	public void registeruser() {

		User user = new User();
		user.setFirstName("Apurva");
		user.setSurName("Deshmukh");
		user.setPincode(686868L);
		user.setEmail("sd770977@gmail.com");

		Mockito.when(userDao.save(user)).thenReturn(user);

		String actualMessage = userService.userRegistration(user);
		assertEquals("user registerd successfully!!", actualMessage);
	}

	@Test
	public void fetchuserById() {

		User user = new User();
		user.setCountry("India");
		user.setEmail("sd770977@gmail.com");
		user.setUserId(1);

		Mockito.when(userDao.findById(1)).thenReturn(Optional.of(user));

		User actualUser = userService.fetchUserById(1);

		assertThat(actualUser).isEqualTo(user);

	}

	@Test

	public void removeUser() {
		User user = new User();
		user.setUserId(1);

		Mockito.doNothing().when(userDao).deleteById(user.getUserId());

		String message = userService.removeUser(user.getUserId());

		assertThat(message).isEqualTo("user is deleted");

	}

	@Test(expected = InvalidId.class)
	public void removeUserById() {
		/*
		 * User user = new User(); user.setUserId(1);
		 */
		// Mockito.doNothing().when(userDao).deleteById(0);

		userService.removeUser(0);

		// assertThat(message).isEqualTo("user is deleted");

	}

	@Test
	public void updateUser() {

		User user = new User();
		user.setFirstName("Apurva");
		user.setSurName("Deshmukh");
		user.setPincode(686868L);
		user.setEmail("sd770977@gmail.com");
		user.setAge(65);
		user.setUserId(1);

		when(userDao.findById(user.getUserId())).thenReturn(Optional.of(user));
		
		user.setAge(23);
		Mockito.when(userDao.save(user)).thenReturn(user);

		String msg = userService.updateUser(user, user.getUserId());

		assertEquals("updated", msg);

	}

}
