package br.com.fastfood.cashier.domain.service;

import br.com.fastfood.cashier.database.repositories.ProductRepository;
import br.com.fastfood.cashier.domain.entities.Product;
import br.com.fastfood.cashier.domain.usecase.ProductUseCase;
import br.com.fastfood.cashier.rest.dtos.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository productRepository;

    @Override
    public void saveNewProduct(ProductRequest productRequest) {
        var newProduct = new Product(productRequest.name(), productRequest.description(), productRequest.price());
        productRepository.add(newProduct);
    }
}
