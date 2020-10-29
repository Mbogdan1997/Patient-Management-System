package com.utcn.hospital;

import javax.xml.ws.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.utcn.hospital.soa.ActivityServiceImpl;

import javassist.expr.NewArray;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
		Endpoint.publish("http://localhost:8083/soa", new ActivityServiceImpl());
	}
//In eclipse mi o vede
}
