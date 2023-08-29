package br.com.fastfood.cashier.database.infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "productOrder")
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDocument {
    private String id;
    private String orderId;
    private String productId;
    private String status;
}
