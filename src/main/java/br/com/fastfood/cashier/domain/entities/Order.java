package br.com.fastfood.cashier.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class Order {
    private String id;
    private String orderNumber;
    private LocalDateTime orderAt;
    private OrderStatus status;
    private String clientId;
    private List<ProductOrder> products;

    public Order(String id, String orderNumber, LocalDateTime orderAt, OrderStatus status, List<ProductOrder> productOrders, String clientId) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderAt = orderAt;
        this.status = status;
        this.clientId = clientId;
        this.products = productOrders;
    }

    public Order(List<Product> products, String clientId) {
        setId();
        setOrderAt();
        setProducts(products);
        this.clientId = clientId;
        this.status = OrderStatus.WAITING;
        this.orderNumber = Long.toString(System.currentTimeMillis());
    }

    private void setProducts(List<Product> products) {
        this. products = products.stream().map(product -> new ProductOrder(this.id, product)).collect(Collectors.toList());
    }

    private void setId() {
        String id = "order_id-";
        this.id = id.concat(UUID.randomUUID().toString());
    }

    public void setOrderAt() {
        this.orderAt = LocalDateTime.now();
    }

    public void changeOrderStatus(OrderStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getClientId() {
        return clientId;
    }

    public List<ProductOrder> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderAt=" + orderAt +
                ", status=" + status +
                ", clientId='" + clientId + '\'' +
                ", products=" + products +
                '}';
    }
}
