package com.example.demo2.config;

import com.example.demo2.consumer.MessageDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@Slf4j
public class RabbitmqConfig {
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private MessageDelegate messageDelegate;
    @Bean
    public TopicExchange exchange_spring() {
        return new TopicExchange("exchange_spring", true, false);
    }

    @Bean
    public Queue queue_img() {
        return new Queue("queue_img", true);
    }

    @Bean
    public Queue queue_video(){
        return new Queue("queue_video",true);
    }

    @Bean
    public Binding binding001() {
        return BindingBuilder.bind(queue_img()).to(exchange_spring()).with("image.#");
    }

    @Bean
    public Binding binding003(){
        return BindingBuilder.bind(queue_video()).to(exchange_spring()).with("video.#");
    }
    @Bean
    public Queue queue_answer() {
        return new Queue("queue_answer", true);
    }

    @Bean
    public Binding binding002() {
        return BindingBuilder.bind(queue_answer()).to(exchange_spring()).with("answer.#");
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue_answer());
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(3);
        container.setDefaultRequeueRejected(false);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setExposeListenerChannel(true);
        container.setConsumerTagStrategy(queue -> queue + "_" + UUID.randomUUID().toString());

        MessageListenerAdapter adapter = new MessageListenerAdapter(messageDelegate);
        adapter.setDefaultListenerMethod("consumeMessage");
//        adapter.setMessageConverter();
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();

        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();

        Map<String, Class<?>> idClassMapping = new HashMap<String, Class<?>>();
        idClassMapping.put("answer",com.example.demo2.consumer.VO.Answer2User.class);
        javaTypeMapper.setIdClassMapping(idClassMapping);

        jsonMessageConverter.setJavaTypeMapper(javaTypeMapper);

        adapter.setMessageConverter(jsonMessageConverter);
        container.setMessageListener(adapter);
        return container;
    }
}
