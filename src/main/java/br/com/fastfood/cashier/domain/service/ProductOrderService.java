package br.com.fastfood.cashier.domain.service;

import br.com.fastfood.cashier.database.repositories.ProductOrderRepository;
import br.com.fastfood.cashier.database.repositories.ProductRepository;
import br.com.fastfood.cashier.domain.entities.ProductOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final ProductRepository productRepository;

    public void notifyProductOrderIsReady(String productOrderId) {
        ProductOrder productOrder = productOrderRepository.getById(productOrderId);
        productOrder.productIsReady();
        productRepository.saveProductOrder(productOrder);
    }
}
