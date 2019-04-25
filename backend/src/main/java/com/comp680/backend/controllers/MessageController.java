package com.comp680.backend.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comp680.backend.messaging.MyMessage;
import com.comp680.backend.models.User;
import com.comp680.backend.repositories.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;


@CrossOrigin(maxAge = 3600 )
@RestController
public class MessageController {

    static List<MyMessage> messageList;


    @Autowired
    MessageProducer producer;
    @Autowired
    MessageListener listener;

    @Autowired
    UsersRepository userRepo;

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }


    
    public static class MessageProducer {

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        @Autowired
        private KafkaTemplate<String, MyMessage> myMessageKafkaTemplate;

        @Value(value = "${message.topic.name}")
        private String topicName;

        @Value(value = "${mymessage.topic.name}")
        private String myMessageTopicName;

        public void sendMessage(String message) {

            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println(
                            "Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
                }
            });
        }

        public void sendMyMessage(MyMessage message) {

            if(messageList==null)
            {
                messageList = new ArrayList<>();
            }else{
                messageList.add(message);
            }
            
            myMessageKafkaTemplate.send(myMessageTopicName, message);
        }
    }

    public static class MessageListener {


        private CountDownLatch latch = new CountDownLatch(3);

        private CountDownLatch myMessageLatch = new CountDownLatch(1);

        


        @KafkaListener(topics = "${mymessage.topic.name}", containerFactory = "myMessageKafkaListenerContainerFactory")
        public void myMessageListener(MyMessage message) {

            if(messageList==null)
            {
                messageList = new ArrayList<>();
            }else{
                messageList.add(message);
            }
            
            System.out.println("Recieved greeting message: " + message);
            this.myMessageLatch.countDown();
        }

    }

    static final String topic = "360SearchMessages";

 

    @PostMapping("/kafka/publish")
    public boolean publish(@RequestBody MyMessage message) throws JsonProcessingException {
        producer.sendMyMessage(message);
        
        return true;
    }

    @GetMapping("/kafka/subscribe")
    public boolean subscribe(@Payload String payload) {
        try {           
            listener.latch.await(10, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @GetMapping("/kafka/fromUser/{id}")
    public List<User> fromUser(@PathVariable long [] id) {
        List<User> result = new ArrayList<>();
        for (long var : id) {
            result.add(userRepo.findById(var));
        }
        return result;
    }

   
   
    @GetMapping("kafka/from/{id}")
    public List<MyMessage> fromMessages(@PathVariable long id) {

        messageList.stream().forEach(System.out::println);
        List<MyMessage> result = new ArrayList<>();
        messageList.stream()
        .filter(e -> ((e.getFromUser()==id))).forEach(x -> result.add(x) );
        return result;
        
    }

  
    @GetMapping("kafka/to/{id}")
    public List<MyMessage> toMessages(@PathVariable long id) {
        messageList.stream().forEach(System.out::println);
        List<MyMessage> result = new ArrayList<>();
        messageList.stream()
        .filter(e -> ((e.getToUser()==id))).forEach(x -> result.add(x) );
        return result;
        
    }




}