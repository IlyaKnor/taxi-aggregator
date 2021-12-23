package com.knor.taxiaggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfo implements Serializable {
    private String aggregatorName;
    private String orderId;
    private Order order;
    private Offer offer;
    private Driver driver;
    private Vehicle vehicle;
}
