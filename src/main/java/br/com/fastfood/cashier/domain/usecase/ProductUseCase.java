package br.com.fastfood.cashier.domain.usecase;

import br.com.fastfood.cashier.rest.dtos.ProductRequest;

public interface ProductUseCase {

    void saveNewProduct(ProductRequest productRequest);
}
