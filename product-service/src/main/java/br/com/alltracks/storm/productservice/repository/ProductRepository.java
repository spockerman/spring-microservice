package br.com.alltracks.storm.productservice.repository;

import br.com.alltracks.storm.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
