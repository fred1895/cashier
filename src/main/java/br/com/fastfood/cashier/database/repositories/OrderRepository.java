package br.com.fastfood.cashier.database.repositories;

import br.com.fastfood.cashier.domain.entities.Order;

import java.util.List;

public interface OrderRepository {
    void saveOrder(Order order);
    Order getById(String orderId);

    List<Order> getLastTen();
}
