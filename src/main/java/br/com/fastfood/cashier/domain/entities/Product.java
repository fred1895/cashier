package br.com.fastfood.cashier.domain.entities;

import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String description;
    private String price;

    public Product(String name, String description, String price) {
        this.setId();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String id, String name, String description, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    private void setId() {
        this.id = "product_id-".concat(UUID.randomUUID().toString());
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
