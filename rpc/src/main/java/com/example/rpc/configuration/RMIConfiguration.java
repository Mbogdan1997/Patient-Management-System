package com.example.rpc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.example.rpc.rmi.HelloWorldRMI;

@Configuration
public class RMIConfiguration {
	
	@Bean
	RmiProxyFactoryBean rmiProxy() {

		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
		bean.setServiceInterface(HelloWorldRMI.class);
		bean.setServiceUrl("rmi://localhost:1099/helloworldrmi");
		bean.setServiceInterface(HelloWorldRMI.class);

		return bean;
	}

}
