package com.neosoft.user.service;

import java.util.List;

import com.neosoft.user.bean.User;
import com.neosoft.user.exception.UserCreationException;
import com.neosoft.user.exception.UserNotFoundException;

public interface UserService {
	
	
	  public User addUser(User user) throws UserCreationException;
	  
	  public User updateUser(User user) throws UserCreationException;
	  
	  public List<User> getByName(String name) throws UserNotFoundException;
	  
	  public List<User> getBySurname(String surname) throws UserNotFoundException;
	  
	  public List<User> getByPIN(String pin) throws UserNotFoundException;
	  
	  public List<User> getAllUsers() throws UserNotFoundException;
	  
	  public List<User> getAllActiveUsers() throws UserNotFoundException;
	  
	  public void hardDelete(Integer id)throws UserNotFoundException;
	  
	  public void softDelete(Integer id) throws UserNotFoundException;
	  
	  public User getById(Integer id) throws UserNotFoundException;
	 
	
	
	
	
	
}
