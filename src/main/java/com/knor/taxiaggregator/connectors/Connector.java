package com.knor.taxiaggregator.connectors;

import com.knor.taxiaggregator.models.Offer;
import com.knor.taxiaggregator.models.yandex.ApprovedOrderInfo;
import com.knor.taxiaggregator.models.Coordinates;
import com.knor.taxiaggregator.models.Order;


public interface Connector {

   String getAggregatorName();

   Offer getOffer(Order order);

   ApprovedOrderInfo takeOffer(Offer offer);

   ApprovedOrderInfo updateOrder(Long orderId, Coordinates coordinates);

   void cancelOrder(String orderId);
}