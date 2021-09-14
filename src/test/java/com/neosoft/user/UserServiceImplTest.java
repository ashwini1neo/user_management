package com.neosoft.user;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.neosoft.user.bean.User;
import com.neosoft.user.entity.UserEntity;
import com.neosoft.user.exception.UserCreationException;
import com.neosoft.user.exception.UserNotFoundException;
import com.neosoft.user.repository.UserRepo;
import com.neosoft.user.service.UserServiceImpl;
import com.neosoft.user.util.DataMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@Mock
	UserRepo repo;

	@InjectMocks
	UserServiceImpl service;

//	@Test
//	@Ignore
//	public void test_addUser1() throws UserCreationException {
//
//		UserEntity entity = UserEntity.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
//				.email("Rohi@gmail.com").mobile(44234275234L).paZZword("qwertyuy").isActive(false).build();
//		
//		when(repo.save(entity)).thenReturn(entity);
//		
//		User user = DataMapper.entityToBean(entity);
//		
//		//User user2 = service.addUser(user);
//		
//		assertEquals(user, service.addUser(user));
//
//	}

	@Test
	public void testGetAllUsers() throws UserNotFoundException {

		List<UserEntity> userEntity = new ArrayList<UserEntity>();
		userEntity.add(UserEntity.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).paZZword("qwertyuy").isActive(false).build());
		when(repo.findAll()).thenReturn(userEntity);
		assertEquals(1, service.getAllUsers().size());

	}

	@Test
	public void test_getActiveAllUsers() throws UserNotFoundException {

		List<UserEntity> userEntity = new ArrayList<UserEntity>();
		userEntity.add(UserEntity.builder().id(1).name("Rohit").surname("Sharma").address("Ratlam").dob(new Date())
				.email("Rohi@gmail.com").mobile(44234275234L).paZZword("qwertyuy").isActive(true).build());
		when(repo.findAll()).thenReturn(userEntity);
		assertEquals(1, service.getAllActiveUsers().size());

	}

	@Test
	public void test_getByName() throws UserNotFoundException {
		List<UserEntity> users = new ArrayList<UserEntity>();
		users.add(new UserEntity(1, "Ashwini", "Sharma", "Ashwiuni@gmail.com", new Date(), 1345678L, "Mumbai", "452871",
				"abc12", "234567fgh", false, new Date(), new Date()));
		String name = "Rohit";

		// Mock the Object
		when(repo.findByName(name)).thenReturn(users);

		// Convert into Bean/DTO
		List<User> user = new ArrayList<User>();
		for (UserEntity user2 : users) {
			User bean = DataMapper.entityToBean(user2);
			user.add(bean);
		}

		assertEquals(1, service.getByName(name).size());
		assertNotNull(user);
	}

	@Test
	public void test_getBySurname() throws UserNotFoundException {
		List<UserEntity> users = new ArrayList<UserEntity>();
		users.add(new UserEntity(1, "Ashwini", "Sharma", "Ashwiuni@gmail.com", new Date(), 1345678L, "Mumbai", "452871",
				"abc12", "234567fgh", false, new Date(), new Date()));
		String surname = "Sharma";

		// Mock the Object
		when(repo.findBySurname(surname)).thenReturn(users);

		// Convert into Bean/DTO
		List<User> user = new ArrayList<User>();
		for (UserEntity user2 : users) {
			User bean = DataMapper.entityToBean(user2);
			user.add(bean);
		}

		List<User> bySurname = service.getBySurname(surname);
		assertEquals(1, bySurname.size());
		assertNotNull(user);
	}

	@Test
	public void test_getByPin() throws UserNotFoundException {
		List<UserEntity> users = new ArrayList<UserEntity>();
		users.add(new UserEntity(1, "Ashwini", "Sharma", "Ashwiuni@gmail.com", new Date(), 1345678L, "Mumbai", "452871",
				"abc12", "234567fgh", false, new Date(), new Date()));
		String pinCode = "456745";

		// Mock the Object
		when(repo.findByPincode(pinCode)).thenReturn(users);

		// Convert into Bean/DTO
		List<User> user = new ArrayList<User>();
		for (UserEntity user2 : users) {
			User bean = DataMapper.entityToBean(user2);
			user.add(bean);
		}

		assertEquals(1, service.getByPIN(pinCode).size());
		assertNotNull(user);
	}

	@Test
	public void test_gitById() throws UserNotFoundException {

		UserEntity user = new UserEntity(1, "Ashwini", "Sharma", "Ashwiuni@gmail.com", new Date(), 1345678L, "Mumbai",
				"452871", "abc12", "234567fgh", false, new Date(), new Date());
		Integer id = 1;

		// Mock the Object
		when(repo.findById(id)).thenReturn(Optional.of(user));

		// expected result
		Integer id2 = service.getById(id).getId();

		assertEquals(id, id2);

	}

	@Test
	public void test_updateUser() throws UserCreationException {

		UserEntity userEntity = new UserEntity(1, "Ashwini", "Sharma", "Ashwiuni@gmail.com", new Date(), 1345678L,
				"Mumbai", "452871", "abc12", "234567fgh", false, new Date(), new Date());

		when(repo.save(userEntity)).thenReturn(userEntity);

		User user = DataMapper.entityToBean(userEntity);
		User updatedUser = service.updateUser(user);

		assertEquals(user, updatedUser);
	}
	
	
	@Test
	public void test_hDelete() throws UserNotFoundException {

		Integer id = 1;
		
		UserServiceImpl ser=mock(UserServiceImpl.class);

		doNothing().when(ser).hardDelete(id);
		ser.hardDelete(id);

		verify(ser, times(1)).hardDelete(id);
	}

	@Test
	public void test_sDelete() throws UserNotFoundException {

		Integer id = 1;
		
		UserServiceImpl ser=mock(UserServiceImpl.class);

		doNothing().when(ser).softDelete(id);

		ser.softDelete(id);
		verify(ser, times(1)).softDelete(id);
	}

}
