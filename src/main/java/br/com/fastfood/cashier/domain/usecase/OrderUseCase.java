package br.com.fastfood.cashier.domain.usecase;

import br.com.fastfood.cashier.rest.dtos.OrderRequest;
import br.com.fastfood.cashier.rest.dtos.OrderResponse;

import java.util.List;

public interface OrderUseCase {
    void orderAFood(OrderRequest orderRequest);

    List<OrderResponse> listLastTen();
}
