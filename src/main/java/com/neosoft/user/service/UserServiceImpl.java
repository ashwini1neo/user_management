package com.neosoft.user.service;

import static org.hamcrest.CoreMatchers.theInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.user.bean.User;
import com.neosoft.user.entity.UserEntity;
import com.neosoft.user.exception.UserCreationException;
import com.neosoft.user.exception.UserNotFoundException;
import com.neosoft.user.repository.UserRepo;
import com.neosoft.user.util.DataMapper;
import com.neosoft.user.util.PasswordUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Override
	public User addUser(User user) throws UserCreationException {
		user.setActive(true);
		user.setPaZZword(PasswordUtil.getEncodedPwd(user.getPaZZword()));
		UserEntity entity = DataMapper.beanToEntity(user);
		try {

			UserEntity entity2 = repo.save(entity);
			return DataMapper.entityToBean(entity2);
		} catch (Exception e) {
			throw new UserCreationException();
		}
	}

	@Override
	public List<User> getByName(String name) throws UserNotFoundException {
		List<User> users = new ArrayList<User>();

		
		List<UserEntity> findByName = repo.findByName(name);
		if (!findByName.isEmpty()) {
			for (UserEntity userEntity : findByName) {
				User user = new User();
				BeanUtils.copyProperties(userEntity, user);
				users.add(user);
			}
			return users;
		} else {
			throw new UserNotFoundException("Users not available with " + name);
		}
	}

	@Override
	public List<User> getBySurname(String surname) throws UserNotFoundException {
		List<User> users = new ArrayList<User>();
		try {
			List<UserEntity> findBysurname = repo.findBySurname(surname);
			if (findBysurname != null) {
				for (UserEntity userEntity : findBysurname) {
					User user = new User();
					BeanUtils.copyProperties(userEntity, user);
					users.add(user);
				}
				return users;
			}
		} catch (Exception e) {
			throw new UserNotFoundException("User not availabel with this surname: " + surname);
		}
		return users;
	}

	@Override
	public List<User> getByPIN(String pin) throws UserNotFoundException {
		List<User> users = new ArrayList<User>();

		try {
			List<UserEntity> findByPin = repo.findByPincode(pin);
			if (findByPin != null) {
				for (UserEntity userEntity : findByPin) {
					User user = DataMapper.entityToBean(userEntity);
					users.add(user);
				}
				return users;
			}
		} catch (Exception e) {
			throw new UserNotFoundException("User not availabel with this pin: " + pin);
		}
		return users;

	}

	@Override
	public List<User> getAllUsers() throws UserNotFoundException {
		ArrayList<User> allUsers = new ArrayList<User>();
		try {
			List<UserEntity> allUsers1 = repo.findAll();
			if (allUsers1 != null) {
				for (UserEntity userEntity : allUsers1) {
					User user = DataMapper.entityToBean(userEntity);
					allUsers.add(user);
					return allUsers;
				}
			}
		} catch (Exception e) {
			throw new UserNotFoundException("Users not Availabel");
		}
		return allUsers;
	}

	@Override
	public void hardDelete(Integer id) throws UserNotFoundException {
		try {
			if (id > 0 && id != null)
				repo.deleteById(id);
			else
				throw new UserNotFoundException("Negetive or Null Value Not Allowed please enter a positive id");

		} catch (Exception e) {
			throw new UserNotFoundException("User not availabel with id " + id);
		}

	}

	@Override
	public void softDelete(Integer id) throws UserNotFoundException {

		try {
			User user = getById(id);
			user.setActive(false);
			UserEntity userEntity = DataMapper.beanToEntity(user);
			repo.save(userEntity);
		} catch (Exception e) {
			throw new UserNotFoundException("User not availabel with id " + id);
		}
	}

	@Override
	public User updateUser(User user) throws UserCreationException {
		try {
			UserEntity userEntity = DataMapper.beanToEntity(user);
			repo.save(userEntity);
			return DataMapper.entityToBean(userEntity);
		} catch (Exception e) {
			throw new UserCreationException("User Not created");
		}
	}

	@Override
	public User getById(Integer id) throws UserNotFoundException {
		User user = new User();
		try {
			if (id > 0 && id != null) {
				Optional<UserEntity> findById = repo.findById(id);
				// UserEntity findById = repo.getOne(id);
				if (findById.isPresent()) {
					UserEntity userEntity = findById.get();
					user = DataMapper.entityToBean(userEntity);
					return user;
				}
			}
			else {
				throw new UserNotFoundException("User not availabel with id " + id);
			}
		} catch (Exception e) {
			throw new UserNotFoundException("User not availabel with id " + id);
		}

		return user;
	}

	@Override
	public List<User> getAllActiveUsers() throws UserNotFoundException {
		List<User> activeUsers = new ArrayList<User>();
		try {
			List<UserEntity> findAll = repo.findAll();
			List<UserEntity> findActive = findAll.stream().filter(o1 -> o1.isActive()).collect(Collectors.toList());
			for (UserEntity userEntity : findActive) {
				User user = DataMapper.entityToBean(userEntity);
				activeUsers.add(user);
				return activeUsers;
			}

		} catch (Exception e) {
			throw new UserNotFoundException("No active Users Availabel");
		}
		return activeUsers;
	}

}
