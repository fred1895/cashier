package br.com.fastfood.cashier.database.infra.mongorepo;

import br.com.fastfood.cashier.database.infra.model.ProductOrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderMongoDatabaseImpl extends MongoRepository<ProductOrderDocument, String> {
    List<ProductOrderDocument> findAllByOrderId(String orderId);
}
