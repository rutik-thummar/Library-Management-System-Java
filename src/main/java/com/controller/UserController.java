package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDTO;
import com.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "")
	public List<UserDTO> getUserList() {
		return userService.getUserList();
	}

	@PostMapping(path = "")
	public UserDTO addUser(@Valid @RequestBody UserDTO userDTO) {
		return userService.add(userDTO);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	}

	@GetMapping(path = "/{email}")
	public UserDTO getRecordByEmail(@PathVariable("email") String email) {
		return userService.getUserByEmail(email);
	}

	@PutMapping(path = "")
	public UserDTO updateUser(@RequestBody UserDTO userDTO) {
		return userService.updateUser(userDTO);
	}

}
