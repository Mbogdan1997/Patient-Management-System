package com.example.rpc.ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.rpc.rmi.HelloWorldRMI;
import com.example.rpc.session.CurentSession;
import com.example.rpc.ui.interval.IntervalIntakeViewTableController;
import com.utcn.hospital.SpringConfiguration;
import com.utcn.hospital.dto.UserDTO;
import com.utcn.hospital.service.DrugService;

@Component
public class LoginController implements ActionListener {

	private PatientLogin patientLogin;
	private HelloWorldRMI helloWorldRMI;

	@Autowired
	public LoginController(PatientLogin patientLogin, HelloWorldRMI helloWorldRMI) {
		// TODO Auto-generated constructor stub
		this.patientLogin = patientLogin;
		this.helloWorldRMI = helloWorldRMI;
		patientLogin.addActionListenerOnLogin(this);
		patientLogin.setVisible(true);
	}
	
	public void setFrameVisible(boolean visible) {
		patientLogin.setVisible(visible);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(patientLogin.getUsername());
		userDTO.setPassword(patientLogin.getPassword());
		UserDTO user = helloWorldRMI.login(userDTO);
		if (user != null) {
			CurentSession.setCurentUser(user);
			this.patientLogin.setVisible(false);
			IntervalIntakeViewTableController intervalIntakeViewTableController=(IntervalIntakeViewTableController) SpringConfiguration.contextProvider().getApplicationContext().getBean("intervalIntakeViewTableController");
			intervalIntakeViewTableController.makeTimer();
			intervalIntakeViewTableController.loadData();
			//intervalIntakeViewTableController.setViewVisible(true);
		}
		System.out.println(user.getRole());

	}

}
