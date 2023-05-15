package br.com.alltracks.storm.orderservice.service.Impl;

import br.com.alltracks.storm.orderservice.dto.InventoryResponse;
import br.com.alltracks.storm.orderservice.dto.OrderLineItemsDto;
import br.com.alltracks.storm.orderservice.dto.OrderRequest;
import br.com.alltracks.storm.orderservice.event.OrderPlacedEvent;
import br.com.alltracks.storm.orderservice.model.Order;
import br.com.alltracks.storm.orderservice.model.OrderLineIItems;
import br.com.alltracks.storm.orderservice.repository.OrderRepository;
import br.com.alltracks.storm.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final Environment environment;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        String inventoryUrl = environment.getProperty("inventory.url");

        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineIItems> orderLineIItemsList = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineIItemsList(orderLineIItemsList);

        List<String> skuCode = order.getOrderLineIItemsList().stream().map(OrderLineIItems::getSkuCode).toList();

        assert inventoryUrl != null;
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri(
                        inventoryUrl
                        , uriBuilder -> uriBuilder.queryParam("skuCode", skuCode).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::getIsInStock);

        if(allProductsInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(this, order.getOrderNumber()));
            return "Order place sucessfully!";
        }else{
            throw new IllegalArgumentException("Product is not in stock!!");
        }

    }

    private OrderLineIItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineIItems.builder()
                .id(orderLineItemsDto.getId())
                .skuCode(orderLineItemsDto.getSkuCode())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .build();

    }
}
