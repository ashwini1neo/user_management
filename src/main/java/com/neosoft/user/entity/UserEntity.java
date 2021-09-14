package com.neosoft.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SURNAME")
	private String surname;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(name = "MOBILE")
	private Long mobile;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "PINCODE")
	private String pincode;
	// private Address address;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String paZZword;

	@Column(name = "ACTIVE")
	private boolean isActive;

	@Column(name = "JOIN_DATE" ,updatable  = false)
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date joiningDate;

	@Column(name = "UPDT_DATE",updatable = true)
	@Temporal(TemporalType.DATE)
	@UpdateTimestamp
	private Date updatedDate;

}
