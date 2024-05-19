package com.esha.EmployeeManagementRestApi.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esha.EmployeeManagementRestApi.entity.Role;
import com.esha.EmployeeManagementRestApi.service.RoleService;


@RestController
@RequestMapping("/api")
public class RoleController {
	private RoleService roleService;
	
	@Autowired
	public RoleController(RoleService theRoleService) {
		roleService = theRoleService;
	}

	
	
	@GetMapping("/role/rolesList")
	public List<Role> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return roleService.findAll();
	}
	
	@GetMapping("/role/search/{name}")
	public Optional<Role> searchByFirstName(@PathVariable String name) {
		return roleService.findByName(name);
	}
	
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return roleService.saveRole(role);
	}

}
