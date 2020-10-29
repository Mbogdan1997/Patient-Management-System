package com.utcn.hospital.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
	
	 static final String topicExchangeName = "spring-boot-exchange";

	    static final String queueName = "spring-boot";

	    @Bean
	    Queue queue() {
	        return new Queue(queueName, false);
	    }

	    @Bean
	    TopicExchange exchange() {
	        return new TopicExchange(topicExchangeName);
	    }

	    @Bean
	    Binding binding(Queue queue, TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	    }

//	    @Bean
//	    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//	            MessageListenerAdapter listenerAdapter) {
//	        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	        container.setConnectionFactory(connectionFactory);
//	        container.setQueueNames(queueName);
//	        container.setMessageListener(listenerAdapter);
//	        return container;
//	    }

	    @Bean
	    MessageListenerAdapter listenerAdapter(Receiver receiver) {
	        return new MessageListenerAdapter(receiver, "receiveMessage");
	    }
	    
	    @Bean
	    MessageConverter jackson2jsonMessageConverter() {
	    	return new Jackson2JsonMessageConverter();
	    }
	    
	    @Bean
	    public RabbitTemplate rabbitTamplate(ConnectionFactory connectionFactory) {
	    	final RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
	    	rabbitTemplate.setMessageConverter(jackson2jsonMessageConverter());
	    	return rabbitTemplate;
	    
	    }
	
	    
	    

}
