package com.knor.taxiaggregator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    private String make;
    private String model;
    private String color;
    private String vehicleNumber;
}
