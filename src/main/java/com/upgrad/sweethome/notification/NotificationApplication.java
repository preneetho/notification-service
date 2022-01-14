package com.upgrad.sweethome.notification;

import com.upgrad.sweethome.notification.messaging.MessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotificationApplication {



	public static void main(String[] args) {
		SpringApplication.run(MessageConsumer.class, args);
	}



}
