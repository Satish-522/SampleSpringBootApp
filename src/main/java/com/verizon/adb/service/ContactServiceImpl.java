package com.verizon.adb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.adb.dao.ContactRepository;
import com.verizon.adb.model.Contact;

@Service 
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactRepository contactRepo;

	@Override
	public Contact getContactById(long contactId) {
		Contact contact=null;
//		if(contactRepo.existsById(contactId)){  // Data base is hit twice while searching and retrieving
//			contact= contactRepo.findById(contactId).get();
//		}
		Optional<Contact> optContact=contactRepo.findById(contactId);
		if(optContact.isPresent()){// checking if data is available
			contact=optContact.get();// retrieve the data using .get
		}
		return contact;
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepo.findAll();
	}

	@Override
	public Contact addContact(Contact contact) { // save func will check is data is there(then merge) else persist(add)
		return contactRepo.save(contact);
	}

	@Override
	public Contact updateContact(Contact contact) {
		return contactRepo.save(contact);
	}

	@Override
	public boolean deleteContact(long contactId) {
		boolean isDeleted=false;
	
		if(contactRepo.existsById(contactId)){
		
		contactRepo.deleteById(contactId);
		isDeleted=true;}
		return isDeleted;
	}

	
	
	
	@Override
	public boolean existsByMobileNUmber(String mobileNUmber) {

		return contactRepo.existsByMobileNUmber(mobileNUmber);
	}

	
	@Override
	public boolean existsByEmailId(String emailId) {
	
		return contactRepo.existsByEmailId(emailId);
	}

	@Override
	public Contact findByMobileNumber(String mobileNUmber) {
		
		return contactRepo.findByMobileNUmber(mobileNUmber);
	}

	@Override
	public Contact findByEmailId(String emailId) {
		
		return contactRepo.findByEmailId(emailId);
	}

	@Override
	public List<Contact> findAllByLastName(String lastName) {
		
		return contactRepo.findAllByLastName(lastName);
	}

}
