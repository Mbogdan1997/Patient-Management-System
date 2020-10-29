package com.utcn.hospital.rmi;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.utcn.hospital.SpringConfiguration;
import com.utcn.hospital.dto.DrugDTO;
import com.utcn.hospital.dto.IdDTO;
import com.utcn.hospital.dto.IntakeIntervalDTO;
import com.utcn.hospital.dto.RMINotification;
import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.enumeration.Role;
import com.utcn.hospital.repository.MedicationPlanRepository;
import com.utcn.hospital.service.DrugService;
import com.utcn.hospital.service.IntakeIntervalService;
import com.utcn.hospital.service.UserService;

@Component
public class HelloWorldRMIimpl implements HelloWorldRMI {

	private DrugService drugService = (DrugService) SpringConfiguration.contextProvider().getApplicationContext()
			.getBean("drugService");

	private UserService userService = (UserService) SpringConfiguration.contextProvider().getApplicationContext()
			.getBean("userService");

	private IntakeIntervalService intakeIntervalService = (IntakeIntervalService) SpringConfiguration.contextProvider()
			.getApplicationContext().getBean("intakeIntervalService");

	public List<DrugDTO> sayHelloRmi() {
		System.out.println("================Server Side ========================");

		return drugService.viewAll();

	}

	@Override
	public UserDTO login(UserDTO userDTO) {
		// TODO Auto-generated method stub
		UserDTO userDTO1 = userService.login(userDTO);
		if (userDTO1 != null) {
			if (userDTO1.getRole() == Role.PATIENT) {
				return userDTO1;
			}
		}

		return null;
	}

	@Override
	public List<IntakeIntervalDTO> viewPacientDayDrug(Date date, IdDTO user) {
		// TODO Auto-generated method stub
		return intakeIntervalService.drugsForUserBetweenDates(date, user);
	}

	@Override
	public void showNotification(RMINotification notification) {
		System.out.println();
		if (notification.isTakeIt()) {
			System.out.println(notification.getPatientName() + " a luat " + notification.getDrugName()
					+ " in intervalul " + notification.getStartHour() + "-" + notification.getFinishHour());
		}

		else {
			System.out.println(notification.getPatientName() + " nu a luat " + notification.getDrugName()
					+ " in intervalul " + notification.getStartHour() + "-" + notification.getFinishHour());
		}

	}

}
