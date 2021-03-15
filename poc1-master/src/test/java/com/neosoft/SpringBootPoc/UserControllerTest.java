package com.neosoft.SpringBootPoc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.springbootpoc.entity.User;
import com.neosoft.springbootpoc.exception.InvalidId;
import com.neosoft.springbootpoc.response.DataResponse;
import com.neosoft.springbootpoc.restcontroller.UserController;
import com.neosoft.springbootpoc.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void removUser() throws Exception {

		when(userService.removeUser(Mockito.anyInt())).thenReturn("user is deleted");

		RequestBuilder builder = MockMvcRequestBuilders.delete("/user/remove/hard/1")
				.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();

		ObjectMapper mapper = new ObjectMapper();
		DataResponse dataResponse = mapper.readValue(result.getResponse().getContentAsString(), DataResponse.class);
		assertEquals(dataResponse.getStatus(), HttpStatus.OK);
		assertEquals(dataResponse.getMessage(), "user is deleted");
	}

	@Test(expected = InvalidId.class)
	public void deleteuser() throws Throwable {

		String URI = "/user/remove/hard/1";

		when(userService.removeUser(Mockito.anyInt())).thenThrow(InvalidId.class);
		try {
			mockMvc.perform(MockMvcRequestBuilders.delete(URI)).andReturn();
		} catch (NestedServletException exception) {
			throw exception.getCause();
		}
	}

	@Test
	public void fetchUserById() throws Exception {

		String URI = "/user/fetchuserbyid/1";

		User user = new User();
		user.setAge(23);
		user.setCity("mumbai");

		when(userService.fetchUserById(Mockito.anyInt())).thenReturn(user);

		RequestBuilder builder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();

		ObjectMapper mapper = new ObjectMapper();
		DataResponse dataResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), DataResponse.class);

		HashMap<String, Object> user1 = (HashMap<String, Object>) dataResponse.getBody();

		assertEquals("mumbai", user1.get("city"));
	}

	@Test
	public void registerUser() throws Exception {

		User user = new User();
		user.setAge(23);
		user.setCity("pune");
		user.setFirstName("shubham");
		user.setSurName("deshmukh");
		user.setPincode(765764L);

		ObjectMapper mapper = new ObjectMapper();

		String inputjosn = mapper.writeValueAsString(user);

		when(userService.userRegistration(user)).thenReturn("user registerd successfully!!");

		MvcResult mvcResult = mockMvc
				.perform(post("/user/registeruser").content(inputjosn).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String result = mvcResult.getResponse().getContentAsString();

		DataResponse dataResponse = mapper.readValue(result, DataResponse.class);

		assertEquals(HttpStatus.OK, dataResponse.getStatus());
		assertEquals("user registerd successfully!!", dataResponse.getMessage());
	}

	@Test
	public void updaeUser() throws Exception {

		User user = new User();
		user.setAge(23);
		user.setCity("pune");
		user.setFirstName("shubham");
		user.setSurName("deshmukh");
		user.setPincode(765764L);
		user.setUserId(1);

		user.setEmail("sd770977@gmail.com");
		ObjectMapper mapper = new ObjectMapper();

		String inputJson = mapper.writeValueAsString(user);
		when(userService.updateUser(user, 1)).thenReturn("updated");

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.put("/user/update/1").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		   String content = mvcResult.getResponse().getContentAsString();
			DataResponse dataResponse = mapper.readValue(content, DataResponse.class);

		//   assertEquals(HttpStatus.OK, dataResponse.getStatus());
		   assertEquals("updated", dataResponse.getMessage());
	}

	
	
	
}
