package com.knor.taxiaggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AggregatorOffer {
    private String aggregatorName;
    private Offer offer;
}
