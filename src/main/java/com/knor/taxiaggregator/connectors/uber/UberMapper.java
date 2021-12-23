package com.knor.taxiaggregator.connectors.uber;

import com.knor.taxiaggregator.models.Coordinates;
import com.knor.taxiaggregator.models.Offer;
import com.knor.taxiaggregator.models.Option;
import com.knor.taxiaggregator.models.Tariff;
import com.knor.taxiaggregator.models.uber.UberApproveResponse;
import com.knor.taxiaggregator.models.uber.UberResponse;
import com.knor.taxiaggregator.models.yandex.ApprovedOrderInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UberMapper {

    public Offer mapToOffer(UberResponse uberResponse) {
        List<Option> options = uberResponse.getOptions()
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
                .offerId(uberResponse.getOfferId())
                .distance(uberResponse.getDistance())
                .options(options)
                .build();
    }

    public ApprovedOrderInfo mapApprovedInfo(UberApproveResponse uberApproveResponse) {
        List<Coordinates> destinations = uberApproveResponse.getDestinations()
                .stream()
                .map(destination -> Coordinates.builder()
                        .latitude(destination.getLatitude())
                        .longitude(destination.getLongitude())
                        .build())
                .collect(Collectors.toList());

        return ApprovedOrderInfo.builder()
                .orderId(uberApproveResponse.getOrderId())
                .pickup(uberApproveResponse.getPickup())
                .destinations(destinations)
                .driver(uberApproveResponse.getDriver())
                .vehicle(uberApproveResponse.getVehicle())
                .build();
    }
}
