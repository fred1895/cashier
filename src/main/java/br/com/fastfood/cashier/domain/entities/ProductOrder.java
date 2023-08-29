package br.com.fastfood.cashier.domain.entities;

import java.util.UUID;

public class ProductOrder {
    private String id;
    private String orderId;
    private Product product;
    private ProductOrderStatus status;

    public ProductOrder(String orderId, Product product) {
        setId();
        this.orderId = orderId;
        this.product = product;
        this.status = ProductOrderStatus.NOT_READY;
    }

    public ProductOrder(String id, String orderId, Product product, ProductOrderStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.status = status;
    }

    public void productIsReady() {
        this.status = ProductOrderStatus.READY;
    }

    private void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public ProductOrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", product=" + product +
                ", status=" + status +
                '}';
    }
}
