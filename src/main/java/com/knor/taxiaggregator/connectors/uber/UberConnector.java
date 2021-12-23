package com.knor.taxiaggregator.connectors.uber;

import com.knor.taxiaggregator.connectors.Connector;
import com.knor.taxiaggregator.models.Coordinates;
import com.knor.taxiaggregator.models.Offer;
import com.knor.taxiaggregator.models.Order;
import com.knor.taxiaggregator.models.OrderInfo;
import com.knor.taxiaggregator.models.uber.UberApproveResponse;
import com.knor.taxiaggregator.models.uber.UberResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UberConnector implements Connector {

    private final UberMapper uberMapper;
    private final WebClient webClient;

    public UberConnector(@Value("${uber.url}") String clientUrl, UberMapper uberMapper) {
        this.uberMapper = uberMapper;
        this.webClient = WebClient.create(clientUrl);
    }

    @Override
    public String getAggregatorName() {
        return "Uber";
    }

    @Override
    public Mono<Offer> getOffer(Order order) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/taxi/order")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UberResponse.class)
                .map(uberMapper::mapToOffer);
    }

    @Override
    public OrderInfo takeOffer(Offer offer) {
        UberApproveResponse uberApproveResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/taxi/offer")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UberApproveResponse.class)
                .block();
        return OrderInfo.builder()
                .orderId(uberApproveResponse.getOrderId())
                .driver(uberApproveResponse.getDriver())
                .vehicle(uberApproveResponse.getVehicle())
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
