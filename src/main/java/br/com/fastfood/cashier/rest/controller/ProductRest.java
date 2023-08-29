package br.com.fastfood.cashier.rest.controller;

import br.com.fastfood.cashier.domain.usecase.ProductUseCase;
import br.com.fastfood.cashier.rest.dtos.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRest {

    private final ProductUseCase productUseCase;


    @PostMapping
    void saveNew(@RequestBody ProductRequest productRequest) {
        productUseCase.saveNewProduct(productRequest);
    }

}
