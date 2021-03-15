package com.neosoft.springbootpoc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public @Data class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	@NotNull
	@Size(min = 3, message = "First name should be atleast 3 character")
	private String firstName;

	// @NotNull
	@Size(min = 3, message = "Sur name should be atleast 3 character")
	private String surName;
	// @NotNull
	private int age;
//	@Email
	private String email;
	private String occupation;
	// @NotNull
	private Long pincode;
	private String city;
	private String state;
	private String country;

	private boolean isDeleted;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dob;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")

	private Date doj;

	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", surName=" + surName + ", age=" + age
				+ ", email=" + email + ", occupation=" + occupation + ", pincode=" + pincode + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", isDeleted=" + isDeleted + ", dob=" + dob + ", doj="
				+ doj + "]";
	}
	public User(int userId,
			@NotNull @Size(min = 3, message = "First name should be atleast 3 character") String firstName,
			@Size(min = 3, message = "Sur name should be atleast 3 character") String surName, int age, String email,
			String occupation, Long pincode, String city, String state, String country, boolean isDeleted, Date dob,
			Date doj) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.surName = surName;
		this.age = age;
		this.email = email;
		this.occupation = occupation;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.isDeleted = isDeleted;
		this.dob = dob;
		this.doj = doj;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
