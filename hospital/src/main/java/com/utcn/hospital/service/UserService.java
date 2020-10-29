package com.utcn.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.entity.User;
import com.utcn.hospital.enumeration.Role;
import com.utcn.hospital.mapper.UserMapper;
import com.utcn.hospital.repository.UserRepository;

@Service
public class UserService {

	private UserMapper userMapper;
	private UserRepository userRepository;

	@Autowired
	public UserService(UserMapper userMapper,UserRepository userRepository) {
		
		this.userMapper = userMapper;
		this.userRepository=userRepository;

	}

	public UserDTO login(UserDTO userDTO) {
		// TODO Auto-generated method stub
		System.out.println(userDTO.getUsername());
		User user = userRepository.findByUsername(userDTO.getUsername());
		System.out.println(user.getBirthDate());
		if (user != null && user.getPassword().equals(userDTO.getPassword())) {
			System.out.println("username:"+userDTO.getUsername());
			System.out.println("password:"+userDTO.getPassword());
			System.out.println("id:"+userDTO.getId());
			return userMapper.toDTO(user);
		}
		return null;
	}

}
