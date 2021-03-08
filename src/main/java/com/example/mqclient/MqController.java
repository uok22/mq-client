package com.example.mqclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.mqclient.MqClientApplication.qName;

@RestController
public class MqController {

    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping("/sendit")
    public void sendPerson(@RequestBody Person person) {

        jmsTemplate.convertAndSend(qName, person);
    }
}



