package br.com.fastfood.cashier.database.infra;

import br.com.fastfood.cashier.database.infra.model.ProductDocument;
import br.com.fastfood.cashier.database.infra.model.ProductOrderDocument;
import br.com.fastfood.cashier.database.infra.mongorepo.ProductMongoDatabaseImpl;
import br.com.fastfood.cashier.database.infra.mongorepo.ProductOrderMongoDatabaseImpl;
import br.com.fastfood.cashier.database.repositories.ProductOrderRepository;
import br.com.fastfood.cashier.domain.entities.Product;
import br.com.fastfood.cashier.domain.entities.ProductOrder;
import br.com.fastfood.cashier.domain.entities.ProductOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOrderMongoRepository implements ProductOrderRepository {

    private final ProductOrderMongoDatabaseImpl productOrderMongoDatabase;
    private final ProductMongoDatabaseImpl productMongoDatabase;
    @Override
    public ProductOrder getById(String id) {
        ProductOrderDocument productOrderDocument = productOrderMongoDatabase.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
        ProductDocument productDocument = productMongoDatabase.findById(productOrderDocument.getProductId()).orElseThrow(() -> new RuntimeException("erro nao econtrado"));
        Product product = new Product(productDocument.getId(), productDocument.getName(), productDocument.getDescription(), productDocument.getPrice());
        ProductOrder productOrder = new ProductOrder(productOrderDocument.getId(), productOrderDocument.getOrderId(), product, ProductOrderStatus.valueOf(productOrderDocument.getStatus()));
        return productOrder;
    }
}
