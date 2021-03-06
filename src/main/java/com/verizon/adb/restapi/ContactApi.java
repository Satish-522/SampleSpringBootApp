package com.verizon.adb.restapi;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.adb.model.Contact;
import com.verizon.adb.service.ContactService;

@RestController
@CrossOrigin
@RequestMapping("/contacts")
public class ContactApi {

	@Autowired
	private ContactService service;

	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts() {
		return new ResponseEntity<>(service.getAllContacts(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable("id") long contactId) {
		ResponseEntity<Contact> resp;
		Contact c = service.getContactById(contactId);
		if (c == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(c, HttpStatus.OK);
		return resp;

	}
	@GetMapping("/{field}/{srchValue}")
	public ResponseEntity<List<Contact>> getAllContacts (
		@PathVariable("field") String fieldBy,
		@PathVariable("srchValue") String searchValue)
	{
		ResponseEntity<List<Contact>> resp;
			switch(fieldBy){
			case "mobileNUmber":
				Contact cBYMobNum= service.findByMobileNumber(searchValue);
				if(cBYMobNum!=null){
					resp=new ResponseEntity<>(Collections.singletonList(cBYMobNum),HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "emailId":
				Contact cBYEmail= service.findByEmailId(searchValue);
				if(cBYEmail!=null){
					resp=new ResponseEntity<>(Collections.singletonList(cBYEmail),HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
				break;
			case "lastName":
				List<Contact> results =service.findAllByLastName(searchValue);
				if(results!=null && results.size()!=0){
					
					resp=new ResponseEntity<>(results,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
				break;
				
				default:
					resp= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					break;	
		}
	
		
		return resp;
	}

	@PostMapping
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
		ResponseEntity<Contact> resp = null;

		if (service.existsByEmailId(contact.getEmailId())) {
			resp = new ResponseEntity<Contact>(HttpStatus.ALREADY_REPORTED);
		}
		if (service.existsByMobileNUmber(contact.getMobileNUmber())) {
			resp = new ResponseEntity<Contact>(HttpStatus.ALREADY_REPORTED);
		}
		if (resp == null) {
			Contact c = service.addContact(contact);
			if (c == null)
				resp = new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Contact>(c, HttpStatus.OK);
		}
		return resp;
	}

	@PutMapping
	public ResponseEntity<Contact> updateContact (@RequestBody Contact contact){
		ResponseEntity<Contact> resp = null;
		Contact c= service.getContactById(contact.getContactId());
		if(!contact.getEmailId().equals(c.getEmailId())){
		if(service.existsByEmailId(contact.getEmailId())){
			resp=new ResponseEntity<Contact>(HttpStatus.ALREADY_REPORTED);
		}
		}
		if(!contact.getMobileNUmber().equals(c.getMobileNUmber())){
		if(service.existsByMobileNUmber(contact.getMobileNUmber())){
			resp=new ResponseEntity<Contact>(HttpStatus.ALREADY_REPORTED);
		}}
		if(resp==null){
			c= service.updateContact(contact);
			if(c==null)
				resp=new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
			else 
				resp=new ResponseEntity<Contact>(c,HttpStatus.OK);
		}
		return resp;
	}

	@DeleteMapping("/{id}")	
	public ResponseEntity<Void> deleteContactAction(@PathVariable("id") long contactId) 
	{		
		ResponseEntity<Void> resp = null;		
	boolean isDeleted = service.deleteContact(contactId);		
	if (!isDeleted)			
		resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	else			
		resp = new ResponseEntity<>(HttpStatus.OK);		
	return resp;
	}
	
}
