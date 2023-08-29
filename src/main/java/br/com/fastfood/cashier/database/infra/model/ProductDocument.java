package br.com.fastfood.cashier.database.infra.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "product")
public class ProductDocument {
    private String id;
    private String name;
    private String description;
    private String price;
}
