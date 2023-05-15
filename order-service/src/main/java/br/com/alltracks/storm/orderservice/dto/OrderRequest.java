package br.com.alltracks.storm.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private long id;

    private long orderNumber;

    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
