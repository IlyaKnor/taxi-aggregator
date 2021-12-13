package com.knor.taxiaggregator.models.uber;

import com.knor.taxiaggregator.models.Coordinates;
import com.knor.taxiaggregator.models.Driver;
import com.knor.taxiaggregator.models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UberApproveResponse {
    private Long orderId;
    private Coordinates pickup;
    private List<Coordinates> destinations;
    private Driver driver;
    private Vehicle vehicle;
}
