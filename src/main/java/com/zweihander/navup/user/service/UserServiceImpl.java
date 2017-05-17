package com.zweihander.navup.user.service;



import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zweihander.navup.user.domain.User;
import com.zweihander.navup.user.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User save(User user) {
		
		if(user.getLast_modified_date() == null){
			user.setLast_modified_date(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		}
		if(user.getCreated_date() == null){
			user.setCreated_date(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		}		
		
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		
		User temp = getBy(user.getUsername());

		user.setId(temp.getId());
		
		if (user.getUsername() == null)
		{
			user.setUsername(temp.getUsername());
		}
		if (user.getPassword() == null)
		{
			user.setPassword(temp.getPassword());
		}
		if (user.getFirstname() == null)
		{
			user.setFirstname(temp.getFirstname());
		}
		if (user.getLastname() == null)
		{
			user.setLastname(temp.getLastname());
		}
		if (user.getEmail() == null)
		{
			user.setEmail(temp.getEmail());
		}
		if (user.getCell_number() == null)
		{
			user.setCell_number(temp.getCell_number());
		}
		if (user.getMac_address() == null)
		{
			user.setMac_address(temp.getMac_address());
		}
		if (user.isActivated() == false)
		{
			user.setActivated(temp.isActivated());
		}
		if (user.isIs_admin() == false)
		{
			user.setIs_admin(temp.isIs_admin());
		}
		user.setCreated_date(temp.getCreated_date());
		user.setLast_modified_date(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

		return userRepository.save(user);
	}
	
	@Override
	public User getBy(String username) {
		return userRepository.findOne(username);
	}
	
	@Override
	public void deleteBy(String username){
		User temp = getBy(username);
		userRepository.delete(temp);
	}
	
	@Override
	public boolean authenticate(String username, String password) {
		return userRepository.authenticateUser(username, password);
		//return true;
	}
	
	//-----------------------------------------------------------------------
	
	@Override
	public List<User> save(List<User> userListObj) {
		try{
		userListObj.forEach((userObj)->{
			if(userObj.getCreated_date() == null)
			{
				userObj.setLast_modified_date(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				}
			if(userObj.getLast_modified_date() == null){
				userObj.setCreated_date(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				}
			});
		}catch(Exception e)
		{
			System.out.println("The exception: "+ e);
		}
		return userRepository.save(userListObj);
	}
	
	@Override
	public void deleteBy(List<User> userListObj) {
		userListObj.forEach((userObj)->{
			deleteBy(userObj.getUsername());
		});
		
	}
	
	@Override
	public List<User> getAll() {
		
		return userRepository.findAll();
	}
	
	//-----------------------------------------------------------------------
	@Override
	public User getBy(Long id) {
		return userRepository.findOne(id);
	}
	@Override
	public void deleteBy(Long id) {
		userRepository.delete(id);
	}
}
