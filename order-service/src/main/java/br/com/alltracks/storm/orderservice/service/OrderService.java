package br.com.alltracks.storm.orderservice.service;

import br.com.alltracks.storm.orderservice.dto.OrderRequest;

public interface OrderService {
    public String placeOrder(OrderRequest orderRequest);
}
