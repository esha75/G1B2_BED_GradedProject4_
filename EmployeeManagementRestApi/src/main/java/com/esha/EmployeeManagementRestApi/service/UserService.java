package com.esha.EmployeeManagementRestApi.service;

import java.util.List;
import java.util.Optional;

import com.esha.EmployeeManagementRestApi.entity.User;

public interface UserService {
	public List<User> findAll();
	public User saveUser(User user);
	public Optional<User> findByUserName(String userName);
}
