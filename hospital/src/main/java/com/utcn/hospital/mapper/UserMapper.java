package com.utcn.hospital.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.entity.User;
import com.utcn.hospital.repository.UserRepository;

@Component
public class UserMapper implements AbstractMapper<User,UserDTO> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public UserMapper(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	

	public UserMapper() {
		super();
	}



	@Override
	public Optional<User> toEntityUpdate(UserDTO dto) {
		// TODO Auto-generated method stub
		return userRepository.findById(dto.getId());
	}

	@Override
	public User toEntityInsert(UserDTO dto) {
		// TODO Auto-generated method stub
		return new User(dto);
	}

	@Override
	public UserDTO toDTO(User entity) {
		// TODO Auto-generated method stub
		return new UserDTO(entity);
	}
	
	

}
