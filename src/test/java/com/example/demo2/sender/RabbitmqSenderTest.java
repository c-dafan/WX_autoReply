package com.example.demo2.sender;

import com.example.demo2.consumer.VO.Answer2User;
import com.example.demo2.handler.VO.Image2Rabbit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RabbitmqSenderTest {
    @Autowired
    private RabbitmqSender rabbitmqSender;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void send() throws JsonProcessingException {
        Image2Rabbit rabbit = new Image2Rabbit();
        rabbit.setToUser("aaa");
        rabbit.setDownUrl("aaaaaaaaaaa");
        rabbitmqSender.send_ocr(rabbit);
    }
    @Test
    public void send1() throws JsonProcessingException {
        Answer2User answer2User = new Answer2User();
        answer2User.setAnswer("sdafs");
        answer2User.setToUser("asdfsad");
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("json");
        messageProperties.getHeaders().put("__TypeId__", "answer");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(answer2User);
        Message msg = new Message(json.getBytes(), messageProperties);
        rabbitTemplate.send("exchange_spring", "answer.abc",msg);
    }
}