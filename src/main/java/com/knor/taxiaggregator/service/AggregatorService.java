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
        return connectors.parallelStream()
                .map(connector -> AggregatorOffer.builder()
                        .offer(connector.getOffer(order))
                        .aggregatorName(connector.getAggregatorName())
                        .build())
                .collect(Collectors.toList());
    }

    public ApprovedOrderInfo takeOffer(AggregatorOffer aggregatorOffer) {
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
