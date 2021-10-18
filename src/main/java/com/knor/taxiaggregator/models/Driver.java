package com.knor.taxiaggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    private String phoneNumber;
    private Double rating;
    private String pictureUrl;
    private String name;
}
