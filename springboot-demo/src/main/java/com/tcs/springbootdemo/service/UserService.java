package com.tcs.springbootdemo.service;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.springbootdemo.User;
import com.tcs.springbootdemo.UserNotFoundException;
import com.tcs.springbootdemo.repository.IUserRepository;
import com.tcs.springbootdemo.service.*;

@Service
public class UserService implements  IUserService {

	@Autowired
	IUserRepository userRepository;
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(User user) {
		userRepository.save(user);

    System.out.println("saved");		
	}
	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	@Override
	public Optional<User> getUser(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("user does not exist");
		}
		return user;
	}
	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
	


}
