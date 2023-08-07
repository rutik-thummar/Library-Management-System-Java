package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AdminDTO;
import com.service.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping(path = "")
	public List<AdminDTO> getAdminList() {
		return adminService.getAdminList();
	}

	@PostMapping(path = "")
	public AdminDTO addAdmin(@RequestBody AdminDTO adminDTO) {
		return adminService.add(adminDTO);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		adminService.deleteAdminById(id);
	}
}
