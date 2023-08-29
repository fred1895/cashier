package br.com.fastfood.cashier.rest.dtos;

import br.com.fastfood.cashier.domain.entities.OrderStatus;

public record OrderStatusChange(String orderId, OrderStatus status) {
}
