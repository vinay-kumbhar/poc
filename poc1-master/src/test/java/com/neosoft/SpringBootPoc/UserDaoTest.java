package com.neosoft.SpringBootPoc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.neosoft.springbootpoc.dao.UserDao;
import com.neosoft.springbootpoc.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class UserDaoTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UserDao userDao;

	public User getUser() {

		User user = new User();
		user.setAge(23);
		user.setFirstName("shubham");
		user.setState("mah");
		user.setUserId(1);
		return user;
	}

	@Test
	public void registerUser() {
		User user = new User();
		user.setAge(23);
		user.setFirstName("shubham");
		user.setState("mah");

		Integer id = testEntityManager.persistAndGetId(user, Integer.class);

		assertNotNull(id);

	}

	@Test
	public void removeUser() {

		User user1 = new User();
		user1.setAge(23);
		user1.setFirstName("shubham");
		user1.setState("mah");

		User user2 = new User();
		user2.setAge(25);
		user2.setFirstName("raj");
		user2.setState("UP");

		User saveUser = testEntityManager.persist(user1);
		testEntityManager.persist(user2);

		testEntityManager.remove(saveUser);

		Iterable<User> allUserFromDb = userDao.findAll();

		List<User> userlist = new ArrayList<>();
		for (User users : allUserFromDb) {
			userlist.add(users);
		}

		assertThat(userlist.size()).isEqualTo(1);

	}

	/*
	 * @Test public void testFindProductByName() { // Product product =
	 * userDao.findByName("iPhone 10"); //
	 * assertThat(product.getName()).isEqualTo("iPhone 10");
	 * 
	 * User user3 = getUser();
	 * 
	 * assertThat(user3.getFirstName()).isEqualTo("shubham"); }
	 */
	
	
	
	@Test
	public void fetchUser() {
		
		User user=new User();
		user.setAge(23);
		user.setCity("pune");
		user.setEmail("sd770977@gmail.com");
		user.setFirstName("shubham");
		user.setSurName("deshmukh");
	//	user.setUserId(1);
		testEntityManager.persist(user);
		
		
		User user1=userDao.findById(user.getUserId()).get();
		User user2=userDao.findUserBy("shubham","deshmukh");
		
		assertNotNull(user);
		
		assertEquals(user1.getAge(), user.getAge());
		assertEquals(user2.getAge(), user.getAge());
	}
	
	
	@Test
	public void updateUser() {
		
		User user = new User();
		user.setAge(23);
		user.setFirstName("shubham");
		user.setState("mah");

		Integer id = testEntityManager.persistAndGetId(user, Integer.class);
		
		User user1=userDao.findById(id).get();
		
		user1.setAge(33);
		
		User user4=userDao.save(user1);
		
		assertEquals(user4.getAge(), user1.getAge());
		
		
		
		
		
	}
	
	
}
