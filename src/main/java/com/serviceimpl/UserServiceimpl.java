package com.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.UserDTO;
import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<UserDTO> getUserList() {
		List<User> studList = userRepository.findAll();
		return studList.stream().map(t -> {
			UserDTO stud = new UserDTO();
			modelMapper.map(t, stud);
			return stud;
		}).collect(Collectors.toList());
	}

	@Override
	public UserDTO add(UserDTO userDto) {
		User user = new User();
		modelMapper.map(userDto, user);
		userRepository.save(user);
		return userDto;
	}

	@Override
	public UserDTO updateUser(UserDTO userDto) {
		User user = userRepository.findByEmail(userDto.getEmail());
		if (userDto.getName() == null) {
			user.setPassword(userDto.getPassword());
		} else {
			modelMapper.map(userDto, user);
		}
		userRepository.save(user);
		return userDto;
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public Boolean login(UserDTO userDTO) {
		User stud = userRepository.findByEmail(userDTO.getEmail());
		if (stud != null && stud.getPassword().contentEquals(userDTO.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		User stud = userRepository.findByEmail(email);
		UserDTO user = new UserDTO();
		modelMapper.map(stud, user);
		return user;
	}

}
