package com.knor.taxiaggregator.connectors.yandex;

import com.knor.taxiaggregator.connectors.Connector;
import com.knor.taxiaggregator.models.Offer;
import com.knor.taxiaggregator.models.yandex.ApprovedOrderInfo;
import com.knor.taxiaggregator.models.Coordinates;
import com.knor.taxiaggregator.models.Order;
import com.knor.taxiaggregator.models.yandex.YandexApproveResponse;
import com.knor.taxiaggregator.models.yandex.YandexResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@AllArgsConstructor
public class YandexConnector implements Connector {

    private final YandexMapper yandexMapper;
    private final WebClient webClient;

    @Override
    public String getAggregatorName() {
        return "Yandex";
    }

    @Override
    public Offer getOffer(Order order) {
        YandexResponse yandexResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/taxi/offer")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(YandexResponse.class)
                .block();
        return yandexMapper.mapToOffer(yandexResponse);
    }

    @Override
    public ApprovedOrderInfo approveOrder() {
        YandexApproveResponse yandexApproveResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/taxi/order")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(YandexApproveResponse.class)
                .block();
        return yandexMapper.mapApprovedInfo(yandexApproveResponse);
    }

    @Override
    public ApprovedOrderInfo updateOrder(Long orderId, Coordinates coordinates) {
        throw new UnsupportedOperationException("Not supported yet");
    }


    @Override
    public void cancelOrder(Long orderId) {

    }
}
