package com.neosoft.user.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.neosoft.user.bean.User;
import com.neosoft.user.entity.UserEntity;
@Component
public class DataMapper {
	
	
	 public static UserEntity beanToEntity(User user) {
		 UserEntity userEntity=new UserEntity();  
		 BeanUtils.copyProperties(user, userEntity);
		 return userEntity;
	 }

	 
	 public static User entityToBean(UserEntity userEntity) {
		 User user=new User();  
		 BeanUtils.copyProperties(userEntity, user);
		 return user;
	 }
}
