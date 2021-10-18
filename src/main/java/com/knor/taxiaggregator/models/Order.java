package com.knor.taxiaggregator.models;

import com.knor.taxiaggregator.models.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Coordinates pointOfDeparture;
    private Coordinates destination;
    private Currency currency;
}
