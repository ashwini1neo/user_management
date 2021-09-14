package com.neosoft.user.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserBeanValidationErrorList {

	private List<UserApiError> errorList;
	
	public UserBeanValidationErrorList() {
	}
	
}
