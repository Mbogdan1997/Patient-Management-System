package com.utcn.hospital.controller.rest;

import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utcn.hospital.dto.CaregiverDTO;
import com.utcn.hospital.dto.DoctorDTO;
import com.utcn.hospital.dto.PatientDTO;
import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.enumeration.Role;
import com.utcn.hospital.mapper.UserMapper;
import com.utcn.hospital.service.CaregiverService;
import com.utcn.hospital.service.DoctorService;
import com.utcn.hospital.service.PatientService;
import com.utcn.hospital.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/user",produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserRestController{

	private UserService userService;

	@Autowired
	public UserRestController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/login")
	public UserDTO login(@RequestBody UserDTO userDTO, HttpSession session) {
		// TODO Auto-generated method stub
		UserDTO curentUserDTO = userService.login(userDTO);
		System.out.print("aici");
		if (curentUserDTO != null) {
				System.out.println(curentUserDTO.getRole());
				System.out.println(curentUserDTO.getUsername());
				System.out.println(curentUserDTO.getId());

				session.setAttribute("user",curentUserDTO);
	
			return curentUserDTO;
		}
		return null;
	}

}
