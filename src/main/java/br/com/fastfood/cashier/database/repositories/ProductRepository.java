package br.com.fastfood.cashier.database.repositories;

import br.com.fastfood.cashier.domain.entities.Product;
import br.com.fastfood.cashier.domain.entities.ProductOrder;

import java.util.List;

public interface ProductRepository {
    Product getProduct(String productId);
    List<Product> getProducts(List<String> productIds);
    void add(Product product);

    void saveProductOrder(ProductOrder productOrder);
}
