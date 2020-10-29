package com.example.rpc;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.example.rpc.rmi.HelloWorldRMI;
import com.example.rpc.ui.login.LoginController;
import com.example.rpc.ui.login.PatientLogin;
import com.utcn.hospital.dto.DrugDTO;

@SpringBootApplication
public class RpcApplication {



	public static void main(String[] args) {
		
		
		
		ConfigurableApplicationContext context= new SpringApplicationBuilder(RpcApplication.class).headless(false).run(args);
		HelloWorldRMI helloWorldRMI = context.getBean(HelloWorldRMI.class);
		LoginController loginController=context.getBean(LoginController.class);
	
		

		System.out.println("================Client Side ========================");

		List<DrugDTO> drugDTOs = helloWorldRMI.sayHelloRmi();

		for (DrugDTO drugDTO : drugDTOs) {
			System.out.println(drugDTO.getName());

		}
		
	}

}
