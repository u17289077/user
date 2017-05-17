package com.zweihander.navup.user.service;

import java.util.List;

import com.zweihander.navup.user.domain.User;

public interface UserService {

	// Operations accessible by user
	
	User save(User user);
	User update(User user);
	User getBy(String username);
	void deleteBy(String username);
	
	boolean authenticate(String username, String password);
	// Administrator Bulk Operations
	
	List<User> save(List<User> userListObj);
	void deleteBy(List<User> userListObj);
	List<User> getAll();
	// Methods that utilize ID field
	
	User getBy(Long id);
	void deleteBy(Long id);
	
}
