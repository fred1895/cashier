package br.com.fastfood.cashier.database.infra.mongorepo;

import br.com.fastfood.cashier.database.infra.model.ClientDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientMongoDatabaseImpl extends MongoRepository<ClientDocument, String> {
}
