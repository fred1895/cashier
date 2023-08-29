package br.com.fastfood.cashier.rest.controller;

import br.com.fastfood.cashier.domain.usecase.EmployeeUseCase;
import br.com.fastfood.cashier.domain.usecase.OrderUseCase;
import br.com.fastfood.cashier.rest.dtos.OrderRequest;
import br.com.fastfood.cashier.rest.dtos.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRest {
    private final OrderUseCase orderUseCase;
    private final EmployeeUseCase employeeUseCase;
    @PostMapping
    void newOrder(@RequestBody OrderRequest orderRequest) {
        orderUseCase.orderAFood(orderRequest);
    }

    @PatchMapping("/{orderId}/prepare")
    public void prepare(@PathVariable String orderId) {
        employeeUseCase.prepareFood(orderId);
    }

    @PatchMapping("/{orderId}/ready")
    public void orderReady(@PathVariable String orderId) {
        employeeUseCase.orderReady(orderId);
    }

    @PatchMapping("/{orderId}/delivered")
    public void orderDelivered(@PathVariable String orderId) {
        employeeUseCase.deliverToClient(orderId);
    }

    @GetMapping
    public List<OrderResponse> listOrders() {
        return orderUseCase.listLastTen();
    }
}
