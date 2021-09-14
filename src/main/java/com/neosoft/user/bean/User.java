package com.neosoft.user.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	// personal fields
//	@NotNull(message = "name is mandatory")
//	@Size(min = 3, max = 20, message = "Name length must be between 3 or  20")
//	@Pattern(regexp = "[A-Z][a-z]*",message = "name contains only character not whitspaces and numbers")
//	@NotBlank(message = "Name Mandatory without white spaces")
	private String name;

//	@NotNull
//	@Size(min = 3, max = 20, message = "surname length must be between 2 or  20")
//	@Pattern(regexp = "[A-Z][a-z]*",message = "surname contains only character")
//	@NotBlank(message = "Mandatory without white spaces")
	private String surname;
	
	
//	@NotNull
//	@NotBlank
//	@Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$", message = "Please enter valid email formate")
	private String email;

//	@NotNull()
	//@JsonFormat(shape = Shape.STRING,pattern = "MM/dd/yyyy")
	//@DateTimeFormat(pattern = "MM/dd/yyyy",iso = ISO.DATE)
	private Date dob;
	
//	@NotNull
//	@Pattern(regexp = "[6-9][0-9]{9}")
	private Long mobile;

//	@NotBlank
	private String address;
	
//	@Size(min=6,max=6)
//	@Pattern(regexp="[0-9]")
	private String pincode;

	// Auth fildes
//	@NotBlank
//	@Pattern(regexp="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$")
	private String userName;

//	^ represents starting character of the string.
//	(?=.*[0-9]) represents a digit must occur at least once.
//	(?=.*[a-z]) represents a lower case alphabet must occur at least once.
//	(?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
//	(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) represents a special character that must occur at least once.
//	(?=\\S+$) white spaces don’t allowed in the entire string.
//	.{8, 20} represents at least 8 characters and at most 20 characters.
//	$ represents the end of the string.
//	@NotNull
//	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message="password have atlease one lowercase, one uppercase, one number and one special character")
	private String paZZword;

	
	private boolean isActive;

	
	private Date joiningDate;
	private Date updatedDate;

}
