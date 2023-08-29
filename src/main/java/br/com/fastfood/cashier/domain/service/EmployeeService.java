package br.com.fastfood.cashier.domain.service;

import br.com.fastfood.cashier.database.repositories.OrderRepository;
import br.com.fastfood.cashier.domain.entities.Order;
import br.com.fastfood.cashier.domain.entities.OrderStatus;
import br.com.fastfood.cashier.domain.usecase.EmployeeUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService implements EmployeeUseCase {

    private final OrderRepository orderRepository;
    @Override
    public void sendOrderToKitchen(String order) {

    }

    @Override
    public void prepareFood(String orderId) {
        var order = orderRepository.getById(orderId);
        order.changeOrderStatus(OrderStatus.PREPARING);
        log.info("Preparing order");
        orderRepository.saveOrder(order);
    }

    @Override
    public void orderReady(String orderId) {
        Order order = orderRepository.getById(orderId);
        order.changeOrderStatus(OrderStatus.READY);
        log.info("Order is ready");
        orderRepository.saveOrder(order);
    }

    @Override
    public void deliverToClient(String orderId) {
        Order order = orderRepository.getById(orderId);
        order.changeOrderStatus(OrderStatus.DELIVERED);
        log.info("Order is delivered");
        orderRepository.saveOrder(order);
    }
}
