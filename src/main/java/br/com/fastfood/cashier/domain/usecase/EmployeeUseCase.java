package br.com.fastfood.cashier.domain.usecase;

public interface EmployeeUseCase {
    void sendOrderToKitchen(String order);
    void prepareFood(String orderId);

    void orderReady(String orderId);
    void deliverToClient(String orderId);
}
