package com.knor.taxiaggregator.rest;

import com.knor.taxiaggregator.models.AggregatorOffer;
import com.knor.taxiaggregator.models.Offer;
import com.knor.taxiaggregator.models.Order;
import com.knor.taxiaggregator.models.yandex.ApprovedOrderInfo;
import com.knor.taxiaggregator.service.AggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aggregator")
@RequiredArgsConstructor
public class AggregatorController {

    private final AggregatorService aggregatorService;

    @PostMapping(value = "/offers")
    public ResponseEntity<List<AggregatorOffer>> getOffers(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(aggregatorService.getOffers(order));
    }

    @PostMapping(value = "/offer")
    public ResponseEntity<ApprovedOrderInfo> getOffer(@RequestParam String name, @RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(aggregatorService.approveOrder(name, order));
    }
}
