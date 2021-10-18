package com.knor.taxiaggregator.models.yandex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexOption {
    Integer classLevel;
    String classText;
    Double price;
    Double waitingTime;
}
