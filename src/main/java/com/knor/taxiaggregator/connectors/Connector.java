package com.knor.taxiaggregator.connectors;

import com.knor.taxiaggregator.models.Offer;
import com.knor.taxiaggregator.models.Coordinates;
import com.knor.taxiaggregator.models.Order;
import com.knor.taxiaggregator.models.OrderInfo;
import reactor.core.publisher.Mono;


public interface Connector {

   String getAggregatorName();

   Mono <Offer> getOffer(Order order);

   OrderInfo takeOffer(Offer offer);

   OrderInfo updateOrder(Long orderId, Coordinates coordinates);

   void cancelOrder(String orderId);
}
