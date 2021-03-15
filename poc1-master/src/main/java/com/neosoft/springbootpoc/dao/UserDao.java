package com.neosoft.springbootpoc.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neosoft.springbootpoc.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.firstName=:firstName or u.surName=:surName")
	User findUserBy(@Param("firstName") String firstName, @Param("surName") String lastname);

	@Query("select u from User u where u.firstName=:firstname")
	User fethcUserByName(@Param("firstname") String firstname);

	@Query("select u from User u  where u.firstName=:firstname or u.surName=:surname AND u.pincode=:pincode")
	//@Query("select u.firstName=:firstname, u.surName=:surname, u.pincode=:pincode FROM User u ORDER BY(CASE WHEN firstName IS NULL THEN surName ELSE pincode END)")
//	@Query(SELECT from User u where u.firstname = :firstname AND
//
//			(u.b = :b and u.c = :c). If no record found, then it should try below one
//			(OR u.b = :b and u.c = null). If no record found, then it should try below one
//			(OR u.b = null and u.c = :c). If no record found, then it should try below one
//			(OR u.b = null and u.c = null))
	List<User> fetchBy(@Param("firstname") String firstname, @Param("surname") String surname,

			@Param("pincode") Long pincode);

	@Query(value = "select * from user  ORDER BY  dob ASC ", nativeQuery = true)
	List<User> fetchUsersByDob();

	@Transactional
	@Modifying

	@Query(value = "update User  set isDeleted=true where userId=:userId")
	int softDelete(@Param("userId") int id);

	@Query(value = "select * from user  ORDER BY  doj ASC ", nativeQuery = true)
	List<User> fetchByDoj();



}
