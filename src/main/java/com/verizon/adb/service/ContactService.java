package com.verizon.adb.service;

import java.util.List;

import com.verizon.adb.model.Contact;

public interface ContactService {
	Contact getContactById(long contactId);
	List<Contact> getAllContacts();
	Contact addContact(Contact contact);
	Contact updateContact(Contact contact);
	boolean deleteContact(long contactId);
	

	boolean existsByMobileNUmber(String mobileNUmber);
	boolean existsByEmailId(String emailId);
	Contact findByMobileNumber(String mobileNUmber);
	Contact findByEmailId(String emailId);
	List<Contact> findAllByLastName(String lastName);
}
