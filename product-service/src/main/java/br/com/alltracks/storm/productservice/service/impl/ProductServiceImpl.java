package br.com.alltracks.storm.productservice.service.impl;

import br.com.alltracks.storm.productservice.dto.ProductRequest;
import br.com.alltracks.storm.productservice.dto.ProductResponse;
import br.com.alltracks.storm.productservice.model.Product;
import br.com.alltracks.storm.productservice.repository.ProductRepository;
import br.com.alltracks.storm.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();
    }

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        productRepository.save(product);
        log.info("Product {} saved ", product.getId());

    }
}
