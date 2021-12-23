package com.knor.taxiaggregator.connectors.yandex;

import com.knor.taxiaggregator.models.*;
import com.knor.taxiaggregator.models.yandex.YandexApproveResponse;
import com.knor.taxiaggregator.models.yandex.YandexResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class YandexMapper {

    public Offer mapToOffer(YandexResponse yandexResponse) {
        List<Option> options = yandexResponse.getOptions()
                .stream()
                .map(option -> Option.builder()
                        .waitingTime(option.getWaitingTime())
                        .pricing(option.getPrice())
                        .tariff(Tariff.builder()
                                .id(option.getClassLevel())
                                .displayName(option.getClassText())
                                .build())
                        .build())
                .collect(Collectors.toList());

        return Offer.builder()
                .offerId(yandexResponse.getOfferId())
                .distance(yandexResponse.getDistance())
                .options(options)
                .build();
    }

    public ApprovedOrderInfo mapApprovedInfo(YandexApproveResponse yandexApproveResponse) {
        List<Coordinates> destinations = yandexApproveResponse.getDestinations()
                .stream()
                .map(destination -> Coordinates.builder()
                        .latitude(destination.getLatitude())
                        .longitude(destination.getLongitude())
                        .build())
                .collect(Collectors.toList());

        return ApprovedOrderInfo.builder()
                .orderId(yandexApproveResponse.getOrderId())
                .pickup(yandexApproveResponse.getPickup())
                .destinations(destinations)
                .driver(yandexApproveResponse.getDriver())
                .vehicle(yandexApproveResponse.getVehicle())
                .build();
    }
}
