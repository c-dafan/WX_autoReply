package com.example.demo2;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test1() {
        log.error("{} -- {}", "aa", "bb");
    }

}
