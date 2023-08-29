package br.com.fastfood.cashier.database.infra;

import br.com.fastfood.cashier.database.infra.model.ProductDocument;
import br.com.fastfood.cashier.database.infra.model.ProductOrderDocument;
import br.com.fastfood.cashier.database.infra.mongorepo.ProductMongoDatabaseImpl;
import br.com.fastfood.cashier.database.infra.mongorepo.ProductOrderMongoDatabaseImpl;
import br.com.fastfood.cashier.database.repositories.ProductRepository;
import br.com.fastfood.cashier.domain.entities.Product;
import br.com.fastfood.cashier.domain.entities.ProductOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductMongoRepository implements ProductRepository {

    private final ProductMongoDatabaseImpl productMongoDatabase;
    private final ProductOrderMongoDatabaseImpl productOrderMongoDatabase;

    @Override
    public Product getProduct(String productId) {
        ProductDocument productDocument = productMongoDatabase.findById(productId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Product productById = new Product(productDocument.getId(), productDocument.getName(), productDocument.getDescription(), productDocument.getPrice());
        return productById;
    }

    @Override
    public List<Product> getProducts(List<String> productIds) {
        List<ProductDocument> allById = productMongoDatabase.findAllById(productIds);
        List<Product> productList = allById.stream()
                .map(productDocument ->
                        new Product(
                                productDocument.getId(),
                                productDocument.getName(),
                                productDocument.getDescription(),
                                productDocument.getPrice())).collect(Collectors.toList());

        return productList;
    }

    @Override
    public void add(Product product) {
        var productDocument = new ProductDocument();
        productDocument.setId(product.getId());
        productDocument.setName(product.getName());
        productDocument.setDescription(product.getDescription());
        productDocument.setPrice(product.getPrice());

        productMongoDatabase.save(productDocument);
    }

    @Override
    public void saveProductOrder(ProductOrder productOrder) {
        ProductOrderDocument productOrderDocument = new ProductOrderDocument();
        productOrderDocument.setProductId(productOrder.getProduct().getId());
        productOrderDocument.setOrderId(productOrder.getOrderId());
        productOrderDocument.setStatus(productOrder.getStatus().toString());
        productOrderDocument.setId(productOrder.getId());
        productOrderMongoDatabase.save(productOrderDocument);
    }
}
