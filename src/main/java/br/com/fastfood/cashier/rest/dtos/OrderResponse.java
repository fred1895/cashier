package br.com.fastfood.cashier.rest.dtos;

import br.com.fastfood.cashier.domain.entities.ProductOrder;

import java.util.List;

public record OrderResponse(String orderId,
                            String orderName,
                            String clientName,
                            String orderStatus,
                            List<ProductOrder> productOrders) {
}
