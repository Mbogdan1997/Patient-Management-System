package com.example.messagingrabbitmq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final RabbitTemplate rabbitTemplate;
	private List<Message> messages;
	private final String path = "C:\\Users\\bogdan\\Downloads\\messaging-rabbitmq\\src\\main\\java\\com\\example\\messagingrabbitmq\\activity.txt";

	public Runner(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		try {
			messages = Files.lines(Paths.get(path)).map((line) -> line.split("		")).map((x) -> {
				try {
					Message message= new Message();
					message.setPatientId(Integer.parseInt(x[0]));
					message.setStart(formatter.parse(x[1]));
					message.setEnd(formatter.parse(x[2]));
					message.setActivityName(x[3].replaceAll("\\s+", ""));
					return message;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					return new Message();
				}
			}).collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");

		for (Message message : messages) {
			rabbitTemplate.convertAndSend(RabbitConfig.topicExchangeName, "foo.bar.baz", message);
			//System.out.println(message);
			Thread.sleep(1000);
		}

	}

}