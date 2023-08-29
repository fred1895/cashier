package br.com.fastfood.cashier.database.infra.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(value = "order")
public class OrderDocument {
    private String id;
    private String orderNumber;
    private LocalDateTime orderAt;
    private String status;
    private String clientId;
}
