package com.knor.taxiaggregator.connectors.yandex;

import com.knor.taxiaggregator.connectors.Connector;
import com.knor.taxiaggregator.models.Offer;
import com.knor.taxiaggregator.models.Coordinates;
import com.knor.taxiaggregator.models.Order;
import com.knor.taxiaggregator.models.OrderInfo;
import com.knor.taxiaggregator.models.yandex.YandexApproveResponse;
import com.knor.taxiaggregator.models.yandex.YandexResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class YandexConnector implements Connector {

    private final YandexMapper yandexMapper;
    private final WebClient webClient;

    public YandexConnector(@Value("${yandex.url}") String clientUrl, YandexMapper yandexMapper) {
        this.yandexMapper = yandexMapper;
        this.webClient = WebClient.create(clientUrl);
    }

    @Override
    public String getAggregatorName() {
        return "Yandex";
    }

    @Override
    public Mono<Offer> getOffer(Order order) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/taxi/order")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(YandexResponse.class)
                .map(yandexMapper::mapToOffer);
    }

    @Override
    public OrderInfo takeOffer(Offer offer) {
        YandexApproveResponse yandexApproveResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/taxi/offer")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(YandexApproveResponse.class)
                .block();
        return OrderInfo.builder()
                .orderId(yandexApproveResponse.getOrderId())
                .driver(yandexApproveResponse.getDriver())
                .vehicle(yandexApproveResponse.getVehicle())
                .build();
    }

    @Override
    public OrderInfo updateOrder(Long orderId, Coordinates coordinates) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void cancelOrder(String orderId) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
