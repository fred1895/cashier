package br.com.fastfood.cashier.domain.entities;

import java.util.UUID;

public class Client {
    private String id;
    private String name;

    public Client(String name) {
        setId();
        this.name = name;
    }

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private void setId() {
        String client = "client-";
        this.id = client.concat(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
