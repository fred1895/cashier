package br.com.fastfood.cashier.database.infra;

import br.com.fastfood.cashier.database.infra.model.ClientDocument;
import br.com.fastfood.cashier.database.infra.mongorepo.ClientMongoDatabaseImpl;
import br.com.fastfood.cashier.database.repositories.ClientRepository;
import br.com.fastfood.cashier.domain.entities.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMongoRepository implements ClientRepository {

    private final ClientMongoDatabaseImpl clientMongoDatabase;
    @Override
    public void saveNew(Client client) {
        ClientDocument clientDocument = new ClientDocument();
        clientDocument.setId(client.getId());
        clientDocument.setName(client.getName());
        clientMongoDatabase.save(clientDocument);
    }

    @Override
    public Client findById(String id) {
        ClientDocument clientDocument = clientMongoDatabase.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
        Client client = new Client(clientDocument.getId(), clientDocument.getName());
        return client;
    }
}
