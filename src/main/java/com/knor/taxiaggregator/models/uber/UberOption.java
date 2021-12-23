package com.knor.taxiaggregator.models.uber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UberOption {
    Integer classLevel;
    String classText;
    Double price;
    Double waitingTime;
}
