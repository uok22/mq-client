package com.example.mqclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Date;

@SpringBootApplication
@EnableJms
public class MqClientApplication implements CommandLineRunner {

	static final String qName = "DEV.QUEUE.1"; // A queue from the default MQ Developer container config

	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {

		SpringApplication.run(MqClientApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		// Send a single message with a timestamp
		String msg = "Hello from IBM MQ at " + new Date();

		Person person = new Person("The Person 22");

		// The default SimpleMessageConverter class will be called and turn a String
		// into a JMS TextMessage
		jmsTemplate.convertAndSend(qName, person);
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
