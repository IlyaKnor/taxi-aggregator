package com.knor.taxiaggregator.service;

import com.knor.taxiaggregator.connectors.Connector;
import com.knor.taxiaggregator.models.AggregatorOffer;
import com.knor.taxiaggregator.models.Offer;
import com.knor.taxiaggregator.models.Order;
import com.knor.taxiaggregator.models.OrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AggregatorService {
    private final List<Connector> connectors;
    private final AmqpTemplate template;

    public List<OrderInfo> getOffers(Order order) {
        HashMap<String, Mono<Offer>> offers = new HashMap<>();
        connectors.forEach(connector -> offers.put(
                connector.getAggregatorName().toLowerCase(Locale.ROOT),
                connector.getOffer(order))
        );

        return offers.entrySet().stream()
                .map(entry -> OrderInfo.builder()
                        .order(order)
                        .aggregatorName(entry.getKey())
                        .offer(entry.getValue().block())
                        .build())
                .collect(Collectors.toList());
    }

    public OrderInfo takeOffer(AggregatorOffer aggregatorOffer) {
        template.convertAndSend("order",aggregatorOffer);
        return connectors.stream()
                .filter(connector -> connector.getAggregatorName().equals(aggregatorOffer.getAggregatorName()))
                .findFirst()
                .map(connector -> connector.takeOffer(aggregatorOffer.getOffer()))
                .orElseThrow(() -> new RuntimeException(String.format("Агрегатор %1$s не найден",
                        aggregatorOffer.getAggregatorName())));
    }

    public void cancelOrder(String aggregatorName, String orderId) {
        Connector foundConnector = connectors.stream()
                .filter(connector -> connector.getAggregatorName().equals(aggregatorName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Агрегатор %1$s не найден", aggregatorName)));
        foundConnector.cancelOrder(orderId);
    }
}
