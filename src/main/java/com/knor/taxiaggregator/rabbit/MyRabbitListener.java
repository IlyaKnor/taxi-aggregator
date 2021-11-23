//package com.knor.taxiaggregator.rabbit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//
//@EnableRabbit
//@Component
//public class MyRabbitListener {
//
//    Logger logger = LoggerFactory.getLogger(MyRabbitListener.class);
//
//    @RabbitListener(queues = "myQueue")
//    public void process(String message) {
//        logger.info("Listener success" + message);
//    }
//}
