package br.com.alltracks.storm.orderservice.repository;

import br.com.alltracks.storm.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
