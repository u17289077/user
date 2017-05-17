package com.zweihander.navup.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zweihander.navup.user.domain.User;

//Spring Data JPA provides implementation of CRUD operations at runtime

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	@Query("SELECT u FROM User u WHERE u.username = ?1")
    User findOne(String username);
	
	@Query("SELECT u.password = crypt(?2,u.password) FROM User u WHERE u.username = ?1")
	boolean authenticateUser(String username, String password);
}
