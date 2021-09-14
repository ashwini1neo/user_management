package com.neosoft.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.user.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	
	public List<UserEntity> findByName(String name);
	
	public List<UserEntity> findBySurname(String surname);
	
	public List<UserEntity> findByPincode(String pincode);
}
