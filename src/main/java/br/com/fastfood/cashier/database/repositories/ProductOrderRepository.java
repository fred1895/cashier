package br.com.fastfood.cashier.database.repositories;

import br.com.fastfood.cashier.domain.entities.ProductOrder;

public interface ProductOrderRepository {
    ProductOrder getById(String id);
}
