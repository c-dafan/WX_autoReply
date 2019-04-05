package com.example.demo2.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitmqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send_ocr(Object object) throws JsonProcessingException {
        Message msg = send_header(object);
        rabbitTemplate.send("exchange_spring", "image.ocr", msg);
    }

    public void send_video(Object object) throws JsonProcessingException {
        log.info("send_video");
        Message msg = send_header(object);
        rabbitTemplate.send("exchange_spring", "video.caption", msg);
    }

    private Message send_header(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message msg = new Message(json.getBytes(), messageProperties);
        return msg;
    }
}
