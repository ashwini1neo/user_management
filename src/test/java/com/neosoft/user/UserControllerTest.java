package com.neosoft.user;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.user.bean.User;
import com.neosoft.user.controller.UserController;
import com.neosoft.user.entity.UserEntity;
import com.neosoft.user.exception.UserCreationException;
import com.neosoft.user.exception.UserNotFoundException;
import com.neosoft.user.service.UserService;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = { "com.neosoft.user" })
public class UserControllerTest {

	@Mock
	private UserService service;

	@InjectMocks
	private UserController controller;

	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	public void setController() {

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void test_getAllActiveUsers() throws Exception {

		List<User> users = new ArrayList<User>();

		users.add(User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).paZZword("qwertyuy").isActive(true).build());

		when(service.getAllActiveUsers()).thenReturn(users);

		this.mockMvc.perform(get("/user/allActiveUsers")).andExpect(status().isFound());
		// .andDo(print());

	}

	@Test
	public void test_getAllUsers() throws Exception {

		List<User> users = new ArrayList<User>();

		users.add(User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).paZZword("qwertyuy").isActive(true).build());

		when(service.getAllUsers()).thenReturn(users);

		this.mockMvc.perform(get("/user/allUsers")).andExpect(status().isFound());
		// .andDo(print());

	}

	@Test
	public void test_getUserbyName() throws Exception {
		List<User> users = new ArrayList<User>();

		users.add(User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).paZZword("qwertyuy").isActive(true).build());

		String name = "Rohit";
		when(service.getByName(name)).thenReturn(users);

		this.mockMvc.perform(get("/user/userByName/{name}", name)).andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1)).andDo(print());

	}

	@Test
	public void test_getUserbySurname() throws Exception {
		List<User> users = new ArrayList<User>();

		users.add(User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).paZZword("qwertyuy").isActive(true).build());

		String surname = "Sharma";
		when(service.getBySurname(surname)).thenReturn(users);

		this.mockMvc.perform(get("/user/userBySurname/{surname}", surname)).andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath(".surname").value("Sharma")).andDo(print());

	}

	@Test
	public void test_getUserbyPincode() throws Exception {
		List<User> users = new ArrayList<User>();

		users.add(User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).pincode("123456").paZZword("qwertyuy").isActive(true)
				.build());

		String pincode = "123456";
		when(service.getByPIN(pincode)).thenReturn(users);

		this.mockMvc.perform(get("/user/userByPin/{pin}", pincode)).andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath(".pincode").value("123456")).andDo(print());

	}

	@Test
	public void test_adduser() throws Exception {
		User user=User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).pincode("123456").paZZword("qwertyuy").isActive(true)
				.build();
		
		when(service.addUser(user)).thenReturn(user);
		
		ObjectMapper mapper=new ObjectMapper();
		String userJsonBody = mapper.writeValueAsString(user);
		
		this.mockMvc.perform(post("/user/addUser")
								.content(userJsonBody)
								.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andDo(print());
		
	}
	
	
	@Test
	public void test_updateUser() throws Exception{
		
		User user=User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).pincode("123456").paZZword("qwertyuy").isActive(true)
				.build();
		Integer id=1;
		
		when(service.getById(id)).thenReturn(user);
		when(service.updateUser(user)).thenReturn(user);
		
		ObjectMapper mapper=new ObjectMapper();
		String userJsonBody = mapper.writeValueAsString(user);
		
		
		this.mockMvc.perform(put("/user/update/{id}",id)
				.content(userJsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	
	@Test
	public void test_softDelete() throws Exception{
		
		User user=User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).pincode("123456").paZZword("qwertyuy").isActive(true)
				.build();
		Integer id=1;
		
		
		doNothing().when(service).softDelete(id);
		service.softDelete(id);

		verify(service, times(1)).softDelete(id);
		
		this.mockMvc.perform(put("/user/sdelete/{id}",id)
				.contentType(MediaType.APPLICATION_JSON)
)
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	@Test
	public void test_hrdDelete() throws Exception {
		
		User user=User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).pincode("123456").paZZword("qwertyuy").isActive(true)
				.build();
		Integer id=1;
		String id1="1";
		String msg="User Deleted Permanently";
		
		doNothing().when(service).hardDelete(id);
		service.hardDelete(id);

		verify(service, times(1)).hardDelete(id);
		
		this.mockMvc.perform(delete("/user/hdelete/{id}",id))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	@Test
	public void test_getUserbyDoj() throws Exception {
		List<User> users = new ArrayList<User>();

		users.add(User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).pincode("123456").paZZword("qwertyuy").isActive(true)
				.build());

		when(service.getAllActiveUsers()).thenReturn(users);

		this.mockMvc.perform(get("/user/byDOJ"))
		.andExpect(status().isFound())
		.andDo(print());

	}

	
	@Test
	public void test_getUserbyDob() throws Exception {
		List<User> users = new ArrayList<User>();

		users.add(User.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).pincode("123456").paZZword("qwertyuy").isActive(true)
				.build());

		when(service.getAllActiveUsers()).thenReturn(users);

		this.mockMvc.perform(get("/user/byDOB"))
		.andExpect(status().isFound())
		.andDo(print());

	}

	
	
}
