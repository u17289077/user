package com.zweihander.navup.user.controllers;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zweihander.navup.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.zweihander.navup.user.domain.AdminDTO;
import com.zweihander.navup.user.domain.BaseDTO;
import com.zweihander.navup.user.domain.LogInResponseDTO;
import com.zweihander.navup.user.domain.NotificationResponseDTO;
import com.zweihander.navup.user.domain.User;
import com.zweihander.navup.user.domain.UserDTO;
import com.zweihander.navup.user.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserService userService;

//---------------------------------------------------------------------------------------------------
//								USER specific requests
//---------------------------------------------------------------------------------------------------
	// Method to retrieve user contact details, accepts 'username' from notification module request

	@RequestMapping(value = "/contact/{username}", method = RequestMethod.GET)
	public ResponseEntity<BaseDTO> getContactDetails(@PathVariable("username") String username)
	{
		User user = userService.getBy(username);

		if (user != null)
		{
			BaseDTO DTO;

			DTO = new NotificationResponseDTO(user);

			return new ResponseEntity<BaseDTO>(DTO, HttpStatus.OK);
		}
		return new ResponseEntity<BaseDTO>(HttpStatus.NOT_FOUND);
	}

	// Method to create user record in database, accepts user JSON object from View Module

	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public ResponseEntity<BaseDTO> createUser(@RequestBody User user)
	{
		user = userService.save(user);
		BaseDTO DTO;

		if(user.isIs_admin())
		{
			DTO = new AdminDTO(user);
		}
		else{
			DTO = new UserDTO(user);
		}


		return new ResponseEntity<BaseDTO>(DTO, HttpStatus.CREATED);
	}

	//Method to update user record in database, accepts user JSON object from View module

	@RequestMapping(value = "/updateUser" , method = RequestMethod.PUT)
	public ResponseEntity<BaseDTO> updateUser(@RequestBody User user)
	{
		User temp = userService.getBy(user.getUsername());
		if (temp != null)
		{
			user = userService.update(user);
			BaseDTO DTO;

			if(user.isIs_admin())
			{
				DTO = new AdminDTO(user);
			}
			else{
				DTO = new UserDTO(user);
			}

			return new ResponseEntity<BaseDTO>(DTO, HttpStatus.OK);
		}
		return new ResponseEntity<BaseDTO>(HttpStatus.NOT_FOUND);
	}

	//Method to retrieve user record identified using the 'username' field from database

	@RequestMapping(value = "/findUser/{username}", method = RequestMethod.GET)
	public ResponseEntity<BaseDTO> findUser(@PathVariable("username") String username)
	{
		User user = userService.getBy(username);

		if (user != null)
		{
			BaseDTO DTO;

			if(user.isIs_admin())
			{
				DTO = new AdminDTO(user);
			}
			else{
				DTO = new UserDTO(user);
			}

			return new ResponseEntity<BaseDTO>(DTO, HttpStatus.OK);
		}
		return new ResponseEntity<BaseDTO>(HttpStatus.NOT_FOUND);
	}

	// Method to delete user record identified by 'username' field from database

	@RequestMapping(value = "/deleteUser/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<BaseDTO> deleteUser(@PathVariable("username") String username)
	{
		User user = userService.getBy(username);
		if(user != null)
		{
			userService.deleteBy(username);
			return new ResponseEntity<BaseDTO>(HttpStatus.OK);
		}
		return new ResponseEntity<BaseDTO>(HttpStatus.NOT_FOUND);
	}

	// Method to check if username and password combo are correct

	@RequestMapping(value = "/authenticate" , method = RequestMethod.POST)
	public ResponseEntity<BaseDTO> testLog(@RequestBody User user)
	{
		User temp = userService.getBy(user.getUsername());
		if (temp != null)
		{
			if(userService.authenticate(user.getUsername(), user.getPassword()))
			{
				BaseDTO DTO;
				DTO = new LogInResponseDTO(temp);

				return new ResponseEntity<BaseDTO>(DTO, HttpStatus.ACCEPTED);
			}
			else
			{
				return new ResponseEntity<BaseDTO>(HttpStatus.FORBIDDEN);
			}
		}
		return new ResponseEntity<BaseDTO>(HttpStatus.NOT_FOUND);
	}


//---------------------------------------------------------------------------------------------------
//								ADMINISTRATOR specific requests
//---------------------------------------------------------------------------------------------------

	// Method for bulk creation of users

	@RequestMapping(value = "/admin/registerUsers" , method = RequestMethod.POST)
	public ResponseEntity<List<User>> createUsers()
	{
		try{
			String csvFilename = "src/main/resources/File.csv";
			if (!csvFilename.isEmpty()) {
				List<User> users = readCSV(csvFilename);

				users = userService.save(users);
				return new ResponseEntity<List<User>>(users, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e)
		{
			System.out.println(e);
			return new ResponseEntity<List<User>>(HttpStatus.CONFLICT);
		}
	}

	// Method for bulk deletion of users

	@RequestMapping(value = "/admin/deleteUsers", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUsers()
	{
		String csvFilename = "src/main/resources/File.csv";
		List<User> users = readCSV(csvFilename);

		Iterator<User> iterator = users.listIterator();
		while(iterator.hasNext())
		{
			if(userService.getBy(iterator.next().getUsername()) == null)
			{
				return new ResponseEntity<User>(iterator.next(),HttpStatus.NOT_FOUND);
			}
		}
		userService.deleteBy(users);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	//Method to retrieve all user records from database

	@RequestMapping(value = "/admin/findAll", method = RequestMethod.GET)
	public ResponseEntity <List<User>> findAll()
	{
		List<User> users = userService.getAll();
		if (users != null)
		{
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
	}

	//---------------------------------------------------------------------------------------------------

	// Method to retrieve user record identified by 'id' field from database 

	@RequestMapping(value = "/admin/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") Long id)
	{
		User user = userService.getBy(id);
		if (user == null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// Method to delete user record identified by 'id' field from database 

	@RequestMapping(value = "/admin/deleteById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteById(@PathVariable("id") Long id)
	{
		User user = userService.getBy(id);
		if(user != null)
		{
			userService.deleteBy(id);
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	public List<User> readCSV(String csvFile)
	{

		CSVReader reader = null;
		List<User> csvContent = new ArrayList<User>();

		try{
			reader = new CSVReader(new FileReader(csvFile),',','"',1);
			String[] line;

			while ((line = reader.readNext()) != null){
				csvContent.add(new User(line[0], line[1], line[2], line[3], line[4], line[5], Boolean.parseBoolean(line[6]), Boolean.parseBoolean(line[7])));
				System.out.println(line[0] + "," + line[1] + "," + line[2] + "," + line[3] + "," + line[4] + "," + line[5] + "," + line[6] + "," + line[7] + "\n");
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		return csvContent;
	}
}