package com.utcn.hospital.rmi;

import java.util.Date;
import java.util.List;

import com.utcn.hospital.dto.DrugDTO;
import com.utcn.hospital.dto.IdDTO;
import com.utcn.hospital.dto.IntakeIntervalDTO;
import com.utcn.hospital.dto.RMINotification;
import com.utcn.hospital.dto.UserDTO;

public interface HelloWorldRMI{
	
	public List<DrugDTO> sayHelloRmi();
	
	public UserDTO login(UserDTO userDTO);
	
	public List<IntakeIntervalDTO> viewPacientDayDrug(Date date,IdDTO user);
	
	public void showNotification(RMINotification notification);

}
