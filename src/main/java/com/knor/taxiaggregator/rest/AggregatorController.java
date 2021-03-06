package com.knor.taxiaggregator.rest;

import com.knor.taxiaggregator.models.AggregatorOffer;
import com.knor.taxiaggregator.models.Order;
import com.knor.taxiaggregator.models.yandex.ApprovedOrderInfo;
import com.knor.taxiaggregator.service.AggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aggregator")
@RequiredArgsConstructor
public class AggregatorController {

    private final AggregatorService aggregatorService;
    private final AmqpTemplate template;

    @PostMapping(value = "/offers")
    public ResponseEntity<List<AggregatorOffer>> getOffers(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(aggregatorService.getOffers(order));
    }

    @PostMapping(value = "/take-offer")
    public ResponseEntity<ApprovedOrderInfo> takeOffer(@RequestBody AggregatorOffer aggregatorOffer) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(aggregatorService.takeOffer(aggregatorOffer));
    }

    @DeleteMapping(value = "/cancel")
    public ResponseEntity<HttpStatus> cancelOrder(@RequestParam String aggregatorName, @RequestParam String orderId) {
        aggregatorService.cancelOrder(aggregatorName, orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping(value = "/test")
    public ResponseEntity<String> test (@RequestBody String message) {
        template.convertAndSend("myQueue", message);
        return ResponseEntity.ok("Test success");
    }

    @PostMapping(value = "/take")
    public ResponseEntity<ApprovedOrderInfo> takeOf (@RequestBody AggregatorOffer aggregatorOffer) {
         template.convertAndSend("myQueue", aggregatorService.takeOffer(aggregatorOffer));
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(aggregatorService.takeOffer(aggregatorOffer));
    }
}
