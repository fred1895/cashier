package br.com.fastfood.cashier.database.infra.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "client")
public class ClientDocument {
    private String id;
    private String name;
}
