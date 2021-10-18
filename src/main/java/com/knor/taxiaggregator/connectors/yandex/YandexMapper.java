package com.knor.taxiaggregator.connectors.yandex;

import com.knor.taxiaggregator.models.*;
import com.knor.taxiaggregator.models.yandex.ApprovedOrderInfo;
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
                .map(yandexOption -> Option.builder()
                        .waitingTime(yandexOption.getWaitingTime())
                        .pricing(yandexOption.getPrice())
                        .tariff(Tariff.builder()
                                .id(yandexOption.getClassLevel())
                                .displayName(yandexOption.getClassText())
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
        List<Coordinates> destination = yandexApproveResponse.getDestination()
                .stream()
                .map(it -> Coordinates.builder()
                        .latitude(it.getLatitude())
                        .longitude(it.getLongitude())
                        .build())
                .collect(Collectors.toList());

        return ApprovedOrderInfo.builder()
                .orderId(yandexApproveResponse.getOrderId())
                .pickup(yandexApproveResponse.getPickup())
                .destination(destination)
                .driver(yandexApproveResponse.getDriver())
                .vehicle(yandexApproveResponse.getVehicle())
                .build();
    }
}
