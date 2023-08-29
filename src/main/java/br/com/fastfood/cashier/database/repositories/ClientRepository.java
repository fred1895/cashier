package br.com.fastfood.cashier.database.repositories;

import br.com.fastfood.cashier.domain.entities.Client;

public interface ClientRepository {
    void saveNew(Client client);

    Client findById(String id);
}
