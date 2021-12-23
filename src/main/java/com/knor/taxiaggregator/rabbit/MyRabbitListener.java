package com.knor.taxiaggregator.rabbit;

import com.knor.taxiaggregator.models.yandex.ApprovedOrderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MyRabbitListener {

    Logger logger = LoggerFactory.getLogger(MyRabbitListener.class);

    @RabbitListener(queues = "yandexQueue", messageConverter = "jacksonMessageConverter")
    public void process(ApprovedOrderInfo info) {
        logger.info("Listener success" + info);
    }

}
