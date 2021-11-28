package com.knor.taxiaggregator.models.yandex;

import com.knor.taxiaggregator.models.Coordinates;
import com.knor.taxiaggregator.models.Driver;
import com.knor.taxiaggregator.models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovedOrderInfo implements Serializable {
    private Long orderId;
    private Coordinates pickup;
    private List<Coordinates> destinations;
    private Driver driver;
    private Vehicle vehicle;
}
