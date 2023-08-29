package br.com.fastfood.cashier.domain.service;

import br.com.fastfood.cashier.database.repositories.ClientRepository;
import br.com.fastfood.cashier.database.repositories.OrderRepository;
import br.com.fastfood.cashier.database.repositories.ProductRepository;
import br.com.fastfood.cashier.domain.entities.Client;
import br.com.fastfood.cashier.domain.entities.Order;
import br.com.fastfood.cashier.domain.entities.Product;
import br.com.fastfood.cashier.domain.usecase.OrderUseCase;
import br.com.fastfood.cashier.rest.dtos.OrderRequest;
import br.com.fastfood.cashier.rest.dtos.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    @Override
    public void orderAFood(OrderRequest orderRequest) {
        var client = new Client(orderRequest.clientName());
        List<String> list = orderRequest.ids().stream().map(order -> order.id()).collect(Collectors.toList());
        List<Product> products = productRepository.getProducts(list);

        var order = new Order(products, client.getId());

        clientRepository.saveNew(client);
        orderRepository.saveOrder(order);
    }

    @Override
    public List<OrderResponse> listLastTen() {
        List<Order> lastTen = orderRepository.getLastTen();
        return lastTen.stream().map(o -> new OrderResponse(o.getId(), o.getOrderNumber(), o.getClientId(), o.getStatus().toString(), o.getProducts())).collect(Collectors.toList());
    }
}
