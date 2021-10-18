package com.knor.taxiaggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {
    private int offerId;
    private Double distance;
    private List<Option> options;
}
