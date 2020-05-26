package com.ua.flipPhone.user;

import org.springframework.data.repository.CrudRepository;

import com.ua.flipPhone.user.User;


public interface UserRepository extends CrudRepository<User, Integer> {

}
