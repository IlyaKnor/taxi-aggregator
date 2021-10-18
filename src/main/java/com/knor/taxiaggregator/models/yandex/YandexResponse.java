package com.knor.taxiaggregator.models.yandex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YandexResponse {
    private int offerId;
    private String currency;
    private Double distance;
    private List<YandexOption> options;
}
