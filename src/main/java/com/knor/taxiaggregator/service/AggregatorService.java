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
    private final Connector connector;

    public List<AggregatorOffer> getOffers(Order order) {
        return connectors.stream()
                .map(connector -> AggregatorOffer.builder()
                        .offer(connector.getOffer(order))
                        .aggregatorName(connector.getAggregatorName())
                        .build())
                .collect(Collectors.toList());
    }

    public ApprovedOrderInfo approveOrder() {
        return connector.approveOrder();
    }
}
