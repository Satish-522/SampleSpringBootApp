package com.verizon.adb.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="Contacts")
public class Contact {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long contactId;
	
	@NotEmpty(message="firstName cant be empty")
	@Size(min=5,max=15,message="Give Caps casing within 5 - 15 characters")
	private String firstName;
	private String lastName;
	
	@DateTimeFormat(iso=ISO.DATE)
	@NotNull(message="Dob cant be blank")
	@Column(name="dob")
	private Date dob;
	
	@NotNull(message="email is required")
	@Email
	private String emailId;
	
	@NotEmpty(message="cant be null")
	@Pattern(regexp="\\d{10}", message="you are in India remember")
	@Column(name="mno")
	private String mobileNUmber;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	public Contact() {

	}
	public Contact( String firstName, String lastName, Date dob, String emailId,
			String mobileNUmber, Gender gender) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.emailId = emailId;
		this.mobileNUmber = mobileNUmber;
		this.gender = gender;
	}
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNUmber() {
		return mobileNUmber;
	}
	public void setMobileNUmber(String mobileNUmber) {
		this.mobileNUmber = mobileNUmber;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
