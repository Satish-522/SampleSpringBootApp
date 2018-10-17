package com.verizon.adb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.adb.model.Contact;

@Repository //TO REGISTER THIS IN bean.xml
public interface ContactRepository extends JpaRepository<Contact, Long>{//Entity to be persisted, datatype
	
	boolean existsByMobileNUmber(String mobileNUmber);
	boolean existsByEmailId(String emailId);
	
	Contact findByMobileNUmber(String mobileNUmber);
	Contact findByEmailId(String emailId);
	List<Contact> findAllByLastName(String lastName);
}
