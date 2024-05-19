package com.esha.EmployeeManagementRestApi.service;

import java.util.List;
import java.util.Optional;

import com.esha.EmployeeManagementRestApi.entity.Role;

public interface RoleService {
	public List<Role> findAll();
	public Role saveRole(Role role);
	public Optional<Role> findByName(String name);

}
