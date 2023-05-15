package br.com.alltracks.storm.productservice.service;

import br.com.alltracks.storm.productservice.dto.ProductRequest;
import br.com.alltracks.storm.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getProducts();
}
