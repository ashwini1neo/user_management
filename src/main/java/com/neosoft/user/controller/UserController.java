package com.neosoft.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.user.bean.User;
import com.neosoft.user.exception.UserCreationException;
import com.neosoft.user.exception.UserNotFoundException;
import com.neosoft.user.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	
	@Autowired
	private UserService service;
	
	
	//Add User
	@PostMapping(path = "/addUser", consumes = "application/json")
	public ResponseEntity<? extends Object> saveUser(@Valid @RequestBody User user) throws UserCreationException {
		User user2 = service.addUser(user);
		return new ResponseEntity<Object>(user2, HttpStatus.CREATED);
	}
	
	//Get All Active Users List
	@GetMapping("/allActiveUsers")
	public ResponseEntity<?> getAllActiveUsers() throws UserNotFoundException {
		List<User> allUsers = service.getAllActiveUsers();
		return new ResponseEntity<List<User>>(allUsers,HttpStatus.FOUND);
	}
	
	
	//Get All Users List
	@GetMapping("/allUsers")
	public ResponseEntity<? > getAllUsers() throws UserNotFoundException {
		List<User> allUsers = service.getAllUsers();
		return new ResponseEntity<List<User>>(allUsers,HttpStatus.FOUND);
	}

	// Users list By date of joining
	@GetMapping("/byDOJ")
	public ResponseEntity<?> orderByDateOfJoining() throws UserNotFoundException {
		List<User> allUsers = service.getAllActiveUsers();
		List<User> listByDOJ = allUsers.stream().sorted((o1, o2) -> o1.getJoiningDate().compareTo(o2.getJoiningDate()))
				.collect(Collectors.toList());
		return new ResponseEntity<List<User>>(listByDOJ,HttpStatus.FOUND);

	}
	
	//Users list By date of Birth
	@GetMapping("/byDOB")
	public ResponseEntity<?> orderByDateOfBirth() throws UserNotFoundException {
		List<User> allUsers = service.getAllActiveUsers();
		List<User> listByDOB = allUsers.stream().sorted((o1, o2) -> o1.getDob().compareTo(o2.getDob()))
				.collect(Collectors.toList());
		return new ResponseEntity<List<User>>(listByDOB,HttpStatus.FOUND);

	}

	
	//get users by name
	@GetMapping("/userByName/{userName}")
	public ResponseEntity<? > getUserByName(@PathVariable String userName) throws UserNotFoundException{
		List<User> usersByName = service.getByName(userName);
		return new ResponseEntity<List<User>>(usersByName,HttpStatus.FOUND);
	}

	
	//get users by surname
	@GetMapping("/userBySurname/{surname}")
	public ResponseEntity<? > getUserBySurname(@PathVariable String surname) throws UserNotFoundException{
		List<User> usersBySurname = service.getBySurname(surname);
		return new ResponseEntity<List<User>>(usersBySurname,HttpStatus.FOUND);
	}

	
	//get users by pincode
	@GetMapping("/userByPin/{pin}")
	public ResponseEntity<? > getUserByPin(@PathVariable String pin) throws UserNotFoundException{
		List<User> usersByPin = service.getByPIN(pin);
		return new ResponseEntity<List<User>>(usersByPin,HttpStatus.FOUND);
	}

	
	//update user By id
	@PutMapping("update/{id}")
	public ResponseEntity<? extends Object> updateUser(@Valid @RequestBody User user, @PathVariable Integer id) throws UserNotFoundException, UserCreationException {
		User userbyId = service.getById(id);
		if (userbyId.getId() == id) {
			User updatedUser = service.updateUser(user);
			return new ResponseEntity<Object>(updatedUser , HttpStatus.OK);
		}
		return null;
	}
	
	
	@PutMapping("/sdelete/{id}")
	public ResponseEntity<? extends String> softDelete(@PathVariable Integer id) throws UserNotFoundException{
		service.softDelete(id);
		return new ResponseEntity<String>(new String("user active status set to false"),HttpStatus.OK);
	}
	
	@DeleteMapping("/hdelete/{id}")
	public ResponseEntity<?> hardDelete(@PathVariable Integer id) throws UserNotFoundException{
		service.hardDelete(id);
		return new ResponseEntity<String>(new String("user deleted permanantly"),HttpStatus.OK);
	}

}
