package br.com.fastfood.cashier.rest.dtos;

public record ProductOrderResponse(String productOrderId, String name, String status, String quantity) {
}
