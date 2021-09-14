package com.neosoft.user.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserApiError> handleUserNotFoundException(UserNotFoundException unfe) {
		UserApiError userApiError = new UserApiError(HttpStatus.NOT_FOUND, unfe);
		return new ResponseEntity<UserApiError>(userApiError, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<UserApiError> handleAllException(Exception ex) {
		UserApiError userApiError = new UserApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
		return new ResponseEntity<UserApiError>(userApiError, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		UserBeanValidationErrorList validationErrorList = new UserBeanValidationErrorList();
		List<UserApiError> errorsList=new ArrayList<UserApiError>();
		
		ex.getBindingResult().getAllErrors().stream().forEach(objectError->{
			UserApiError userApiError=new UserApiError(objectError.getDefaultMessage(), objectError.getCode(), LocalDateTime.now());
			errorsList.add(userApiError);
		});
		validationErrorList.setErrorList(errorsList);

		return new ResponseEntity<Object>(validationErrorList,HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<UserApiError> handleUserCreationException(UserCreationException uce){
		UserApiError userApiError = new UserApiError(HttpStatus.BAD_REQUEST, uce);
		return new ResponseEntity<UserApiError>(userApiError,HttpStatus.BAD_REQUEST);
	
	}
	}