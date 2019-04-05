package com.example.demo2;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RabbitTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendExtConverterMessage() throws Exception {
        byte[] body = Files.readAllBytes(Paths.get("D:/rabbitmq", "aa.png"));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("image/png");
        messageProperties.getHeaders().put("extName", "png");
        Message message = new Message(body, messageProperties);
        rabbitTemplate.send("", "image_queue", message);
//
//			byte[] body = Files.readAllBytes(Paths.get("D:/rabbitmq", "aa.pdf"));
//			MessageProperties messageProperties = new MessageProperties();
//			messageProperties.setContentType("application/pdf");
//			Message message = new Message(body, messageProperties);
//			rabbitTemplate.send("", "pdf_queue", message);
    }


    @Test
    public void testSendMessage2() throws Exception {
        //1 创建消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plain");
        Message message = new Message("mq 消息1234".getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.abc", message);

        rabbitTemplate.convertAndSend("topic001", "spring.amqp", "hello object message send!");
        rabbitTemplate.convertAndSend("topic002", "rabbit.abc", "hello object message send!");
    }
}
