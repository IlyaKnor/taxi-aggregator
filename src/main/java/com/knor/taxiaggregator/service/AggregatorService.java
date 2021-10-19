package com.knor.taxiaggregator.service;

import com.knor.taxiaggregator.connectors.Connector;
import com.knor.taxiaggregator.models.AggregatorOffer;
import com.knor.taxiaggregator.models.Order;
import com.knor.taxiaggregator.models.yandex.ApprovedOrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AggregatorService {
    private final List<Connector> connectors;

    public List<AggregatorOffer> getOffers(Order order) {
        return connectors.stream()
                .map(connector -> AggregatorOffer.builder()
                        .offer(connector.getOffer(order))
                        .aggregatorName(connector.getAggregatorName())
                        .build())
                .collect(Collectors.toList());
    }

    public ApprovedOrderInfo approveOrder(String aggregatorName, Order order) {
        return connectors.stream()
                .filter(connector -> connector.getAggregatorName().equals(aggregatorName))
                .map(aggregator -> aggregator.approveOrder(aggregatorName,order))
                .findFirst().orElseThrow(() -> new  RuntimeException("Агрегатор по данному имени не найден"));
    }
}
